package es.upm.miw.apaw_practice.domain.models.shopping_center;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class EmployeeTest {

    private EmployeeComponent root;
    private EmployeeComposite composite;
    private EmployeeLeaf leaf1;
    private EmployeeLeaf leaf2;
    private EmployeeLeaf leaf3;

    @BeforeEach
    void ini() {
        this.root = new EmployeeComposite();

        composite = new EmployeeComposite();
        this.root.add(composite);

        leaf1 = new EmployeeLeaf(new Employee("01122233A", "Marcos", "700000001"));
        leaf2 = new EmployeeLeaf(new Employee("01122233B", "Carla", "700000002"));
        composite.add(leaf1);
        composite.add(leaf2);

        leaf3 = new EmployeeLeaf(new Employee("01122233C", "Ruben", "700000003"));
        this.root.add(leaf3);
    }

    @Test
    void testAddRemoveLeaf() {
        EmployeeLeaf leaf4 = new EmployeeLeaf(new Employee("01122233D", "Janna", "700000004"));
        composite.add(leaf4);
        assertTrue(this.root.getName().toList().contains("Janna"));
        composite.remove(leaf4);
        assertFalse(this.root.getName().toList().contains("Janna"));
    }

    @Test
    void testAddRemoveUnsupported() {
        EmployeeLeaf leaf5 = new EmployeeLeaf(new Employee("01122233E", "Peter", "700000005"));
        assertThrows(UnsupportedOperationException.class, () -> leaf1.add(leaf5));
    }

    @Test
    void testIsComposite() {
        assertTrue(this.root.isComposite());
        assertTrue(this.composite.isComposite());
        assertFalse(this.leaf1.isComposite());
        assertFalse(this.leaf2.isComposite());
        assertFalse(this.leaf3.isComposite());
    }

    @Test
    void testGetDni() {
        List<String> names = this.root.getDni().toList();
        assertEquals(3, names.size());
        assertTrue(names.contains("01122233A"));
        assertTrue(names.contains("01122233B"));
        assertTrue(names.contains("01122233C"));
    }

    @Test
    void testGetName() {
        List<String> names = this.root.getName().toList();
        assertEquals(3, names.size());
        assertTrue(names.contains("Marcos"));
        assertTrue(names.contains("Carla"));
        assertTrue(names.contains("Ruben"));
    }

    @Test
    void testGetPhone() {
        List<String> names = this.root.getPhone().toList();
        assertEquals(3, names.size());
        assertTrue(names.contains("700000001"));
        assertTrue(names.contains("700000002"));
        assertTrue(names.contains("700000003"));
    }

    @Test
    void testEmployeeBuilder() {
        Employee employee = Employee.builder()
                .dni("01122233Z")
                .name("Jaime")
                .phone("700000009")
                .build();
        assertEquals("Jaime", employee.getName());
        assertEquals("01122233Z", employee.getDni());
        assertEquals("700000009", employee.getPhone());
    }
}
