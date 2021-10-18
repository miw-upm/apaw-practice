package es.upm.miw.apaw_practice.domain.models.car_hire;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class VehicleCompositeTest {

    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final List<VehicleLeaf> vehiclesLeaf = new ArrayList<>();
    private static final List<VehicleComposite> vehiclesComposite = new ArrayList<>();
    private static final VehicleComposite treeFather = new VehicleComposite("TreeFather");

    @BeforeAll
    static void createVehicles() {
        vehicles.add(Vehicle.builder().vinNumber("8763JAJA").dailyCost(new BigDecimal("50")).kilometersAmount(50000).goodCondition(true).build());
        vehicles.add(Vehicle.builder().vinNumber("6545GGGG").dailyCost(new BigDecimal("45")).kilometersAmount(70000).goodCondition(false).build());
        vehicles.add(Vehicle.builder().vinNumber("HJASB555").dailyCost(new BigDecimal("40")).kilometersAmount(80000).goodCondition(false).build());
        vehicles.add(Vehicle.builder().vinNumber("ASKJ342D").dailyCost(new BigDecimal("55")).kilometersAmount(40000).goodCondition(true).build());
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(0)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(1)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(2)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(3)));
        vehiclesComposite.add(new VehicleComposite("Cars"));
        vehiclesComposite.get(0).add(vehiclesLeaf.get(0));
        vehiclesComposite.get(0).add(vehiclesLeaf.get(1));
        vehiclesComposite.get(0).add(vehiclesLeaf.get(2));
        vehiclesComposite.get(0).add(vehiclesLeaf.get(3));

        vehicles.add(Vehicle.builder().vinNumber("ND3H897Y").dailyCost(new BigDecimal("100")).kilometersAmount(250000).goodCondition(true).build());
        vehicles.add(Vehicle.builder().vinNumber("9E8F98WH").dailyCost(new BigDecimal("90")).kilometersAmount(300000).goodCondition(false).build());
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(4)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(5)));
        vehiclesComposite.add(new VehicleComposite("Trucks"));
        vehiclesComposite.get(1).add(vehiclesLeaf.get(4));
        vehiclesComposite.get(1).add(vehiclesLeaf.get(5));

        vehicles.add(Vehicle.builder().vinNumber("DFWQ8E7F").dailyCost(new BigDecimal("120")).kilometersAmount(300000).goodCondition(true).build());
        vehicles.add(Vehicle.builder().vinNumber("9284YR3E").dailyCost(new BigDecimal("110")).kilometersAmount(350000).goodCondition(false).build());
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(6)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(7)));
        vehiclesComposite.get(1).add(new VehicleComposite("Trucks: Gasoline"));
        vehiclesComposite.get(1).getVehicleComponents().get(2).add(vehiclesLeaf.get(6));
        vehiclesComposite.get(1).getVehicleComponents().get(2).add(vehiclesLeaf.get(7));

        vehicles.add(Vehicle.builder().vinNumber("878YR784").dailyCost(new BigDecimal("150")).kilometersAmount(400000).goodCondition(true).build());
        vehicles.add(Vehicle.builder().vinNumber("784IDS83").dailyCost(new BigDecimal("130")).kilometersAmount(500000).goodCondition(false).build());
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(8)));
        vehiclesLeaf.add(new VehicleLeaf(vehicles.get(9)));
        vehiclesComposite.get(1).add(new VehicleComposite("Trucks: Diesel"));
        vehiclesComposite.get(1).getVehicleComponents().get(3).add(vehiclesLeaf.get(8));
        vehiclesComposite.get(1).getVehicleComponents().get(3).add(vehiclesLeaf.get(9));

        treeFather.add(vehiclesComposite.get(0));
        treeFather.add(vehiclesComposite.get(1));
    }

    @Test
    void testVehicleLeaf() {
        assertThrows(UnsupportedOperationException.class, () -> vehiclesLeaf.get(0).add(vehiclesLeaf.get(1)));
        assertFalse(vehiclesLeaf.get(0).isComposite());
        assertEquals(1, vehiclesLeaf.get(0).numberOfNodes());
    }

    @Test
    void testCompositePattern() {
        assertTrue(vehiclesComposite.get(0).isComposite());
        assertEquals("Cars", vehiclesComposite.get(0).getName());
        vehiclesComposite.get(0).remove(vehiclesLeaf.get(0));
        assertEquals(3, vehiclesComposite.get(0).getVehicleComponents().size());
        assertNotEquals(vehiclesLeaf.get(0), vehiclesComposite.get(0).getVehicleComponents().get(0));
        assertEquals(4, vehiclesComposite.get(0).numberOfNodes());
        vehiclesComposite.get(0).add(vehiclesLeaf.get(0));
        assertEquals(5, vehiclesComposite.get(0).numberOfNodes());

        assertEquals(3, vehiclesComposite.get(1).getVehicleComponents().get(2).numberOfNodes());

        assertEquals(3, vehiclesComposite.get(1).getVehicleComponents().get(3).numberOfNodes());

        assertEquals(9, vehiclesComposite.get(1).numberOfNodes());

        assertEquals(15, treeFather.numberOfNodes());
    }

    @AfterAll
    static void deleteAll() {
        vehicles.clear();
        vehiclesLeaf.clear();
        vehiclesComposite.clear();
    }
}
