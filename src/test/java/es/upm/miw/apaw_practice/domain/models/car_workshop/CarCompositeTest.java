package es.upm.miw.apaw_practice.domain.models.car_workshop;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CarCompositeTest {

    private CarLeaf car1;
    private CarLeaf car2;
    private CarComposite composite1;

    @BeforeEach
    void before() {
        List<TyreSpecification> tyreSpecs = List.of(
                new TyreSpecification(15, 205, "90V", new ArrayList<>()),
                new TyreSpecification(16, 195, "100T", new ArrayList<>())
        );
        car1 = new CarLeaf(new Car("1234ABC", true, new Owner(), tyreSpecs));
        car2 = new CarLeaf(new Car("5678DEF", false, new Owner(), new ArrayList<>()));

        composite1 = new CarComposite("composite1");
        composite1.add(car1);
    }

    @Test
    void testCarAsCompositeLeaf() {
        assertFalse(car1.isComposite());
        assertFalse(car2.isComposite());
    }

    @Test
    void testCreateCarComposite() {
        assertEquals("composite1", composite1.getName());
        assertTrue(composite1.isComposite());
    }

    @Test
    void testCarCompositeAdd() {
        composite1.add(car2);
        assertEquals(2, composite1.numberOfNodes());
    }

    @Test
    void testCarCompositeRemove() {
        composite1.remove(car1);
        assertEquals(0, composite1.numberOfNodes());
    }
}
