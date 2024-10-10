package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class CarPersistenceMongodbIT {

    @Autowired
    private CarPersistenceMongodb carPersistence;

    @Autowired
    private OwnerCarPersistenceMongodb ownerCarPersistence;

    @Autowired
    private PiecePersistenceMongodb piecePersistence;

    @Test
    void testReadAll() {
        List<Car> cars = this.carPersistence.readAll().toList();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    public void testReadByModel(){
        Car car = this.carPersistence.readByModel("Model 3");
        assertEquals("Model 3", car.getModel());
        assertEquals(true, car.isElectric());
        assertEquals(new BigDecimal(40000), car.getPrice());
        assertTrue(car.getPieces()
                .stream()
                .map(Piece::getPartNumber)
                .collect(Collectors.toList())
                .contains("WSDF"));
        assertTrue(car.getOwner().getName().equals("Marcos"));

    }

    @Test
    public void testCreateAndDelete(){
        OwnerCar ownerCar = this.ownerCarPersistence.readByDriverLicense("GHJFDG");
        Piece piece1 = this.piecePersistence.readByPartNumber("WSDF");
        Piece piece2 = this.piecePersistence.readByPartNumber("SFGE");
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(piece1);
        pieceList.add(piece2);
        Car car = new Car("Supra", false, new BigDecimal(35000), ownerCar, pieceList);

        this.carPersistence.create(car);
        assertEquals("Supra", car.getModel());
        assertEquals(false, car.isElectric());
        assertEquals(new BigDecimal(35000), car.getPrice());
        this.carPersistence.delete("Supra");

    }

    @Test
    void testGetTotalCostByDriverLicense() {
        String driverLicense = "UCD253";
        BigDecimal sumCost = this.carPersistence.getTotalCostByDriverLicense(driverLicense);
        assertEquals(new BigDecimal("400"), sumCost);
    }
}
