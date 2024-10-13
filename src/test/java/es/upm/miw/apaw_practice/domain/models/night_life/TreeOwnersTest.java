package es.upm.miw.apaw_practice.domain.models.night_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeOwnersTest {
    private TreeOwners root;
    private TreeOwners subGroup;
    private TreeOwners leaf1;
    private TreeOwners leaf2;

    @BeforeEach
    void ini() {
        this.root = new TreeOwnersComposite("Main Group");
        this.leaf1 = new TreeOwnersLeaf(new Owner("Carlota", "123456789", "carlota@example.com"));
        this.leaf2 = new TreeOwnersLeaf(new Owner("Jose", "987654321", "jose@example.com"));

        this.root.add(leaf1);
        this.subGroup = new TreeOwnersComposite("Sub Group");
        this.root.add(subGroup);
        this.subGroup.add(leaf2);
    }
    @Test
    void testDetailsIfLeaf() {
        assertEquals("Carlota", this.leaf1.getName());
        assertEquals("123456789", this.leaf1.getPhone());
        assertEquals("carlota@example.com", this.leaf1.getEmail());
    }

    @Test
    void testIsComposite() {
        assertFalse(this.leaf1.isComposite());
        assertTrue(this.root.isComposite());
    }

    @Test
    void testAddLeafToComposite() {
        TreeOwners newLeaf = new TreeOwnersLeaf(new Owner("Raul", "456123789", "raul@example.com"));
        this.subGroup.add(newLeaf);
        assertEquals(2, ((TreeOwnersComposite) this.subGroup).getOwnerComponents().size());
    }

    @Test
    void testRemoveLeafFromComposite() {
        this.subGroup.remove(leaf2);
        assertEquals(0, ((TreeOwnersComposite) this.subGroup).getOwnerComponents().size());
    }

    @Test
    void testGetNameRecursive() {
        assertEquals("Main Group: Carlota, Sub Group: Jose", this.root.getName());
    }

    @Test
    void testGetPhoneRecursive() {
        assertEquals("123456789, 987654321", this.root.getPhone());
    }
    @Test
    void testGetEmailRecursive() {
        assertEquals("carlota@example.com, jose@example.com", this.root.getEmail());
    }
}
