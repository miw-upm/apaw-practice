package es.upm.miw.apaw_practice.domain.models.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private AnimalComponent animalRoot;
    private AnimalComponent animalComposite;
    private AnimalComponent animalLeaf;

    @BeforeEach
    void ini() {
        this.animalRoot = new AnimalComposite();

        Animal animal1 = new Animal("Lara", 10,
                LocalDateTime.of(2014, 8, 10, 15, 10),
                new OwnerClinic("Marcos", "Street Toledo", "980453215"));
        Animal animal2 = new Animal("Toy", 5,
                LocalDateTime.of(2017, 7, 4, 2, 58),
                new OwnerClinic("Mary", "Street Principal", "852693147"));

        this.animalLeaf = new AnimalLeaf(animal1);
        AnimalLeaf animalLeaf2 = new AnimalLeaf(animal2);

        this.animalRoot.add(animalLeaf);
        this.animalRoot.add(animalLeaf2);

        this.animalComposite = new AnimalComposite();
        Animal animal3 = new Animal("Mel", 2,
                LocalDateTime.of(2022, 10, 11, 5, 41),
                new OwnerClinic("Aitana", "Street San Juan", "651234879"));
        this.animalComposite.add(new AnimalLeaf(animal3));
        this.animalComposite.add(new AnimalLeaf(new Animal("Lili", 10,
                LocalDateTime.of(2014, 8, 10, 15, 10),
                new OwnerClinic("Juan", "Street Segundo", "841256798"))));

        this.animalRoot.add(animalComposite);
    }

    @Test
    void testIsComposite() {
        assertTrue(this.animalRoot.isComposite());
        assertTrue(this.animalComposite.isComposite());
    }

    @Test
    void testIsNotComposite() {
        assertFalse(this.animalLeaf.isComposite());
    }

    @Test
    void testAddLeaf() {
        Animal animal = new Animal("Lili", 10,
                LocalDateTime.of(2014, 8, 10, 15, 10),
                new OwnerClinic("Juan", "Street Segundo", "841256798"));
        assertThrows(UnsupportedOperationException.class, () -> this.animalLeaf.add(new AnimalLeaf(animal)));
    }

    @Test
    void testRemoveLeaf() {
        Animal animal = new Animal("Lili", 10,
                LocalDateTime.of(2014, 8, 10, 15, 10),
                new OwnerClinic("Juan", "Street Segundo", "841256798"));
        assertThrows(UnsupportedOperationException.class, () -> this.animalLeaf.remove(new AnimalLeaf(animal)));
    }

    @Test
    void testAddRemoveComponents() {
        Animal animal = new Animal("Lili", 10,
                LocalDateTime.of(2014, 8, 10, 15, 10),
                new OwnerClinic("Juan", "Street Segundo", "841256798"));
        AnimalLeaf leaf = new AnimalLeaf(animal);

        this.animalRoot.add(leaf);
        assertTrue(this.animalRoot.getName().toList().contains("Lili"));
    }

    @Test
    void testGetName() {
        List<String> animalNames = this.animalRoot.getName().toList();
        assertEquals(4, animalNames.size());
        assertTrue(animalNames.contains("Lara"));
        assertTrue(animalNames.contains("Lili"));
        assertTrue(animalNames.contains("Toy"));
        assertTrue(animalNames.contains("Mel"));
    }

    @Test
    void testGetAge() {
        List<Integer> ages = this.animalRoot.getAge().toList();
        assertEquals(4, ages.size());
        assertTrue(ages.contains(10));
        assertTrue(ages.contains(2));
        assertTrue(ages.contains(5));
    }

    @Test
    void testGetDateOfService() {
        List<LocalDateTime> dateTimes = this.animalRoot.getDateOfService().toList();
        assertEquals(4, dateTimes.size());
        assertTrue(dateTimes.contains(LocalDateTime.of(2014, 8, 10, 15, 10)));
        assertTrue(dateTimes.contains(LocalDateTime.of(2014, 8, 10, 15, 10)));
        assertTrue(dateTimes.contains(LocalDateTime.of(2017, 7, 4, 2, 58)));
        assertTrue(dateTimes.contains(LocalDateTime.of(2022, 10, 11, 5, 41)));
    }

    @Test
    void testGetOwnerClinic() {
        List<OwnerClinic> ownerClinics = this.animalRoot.getOwnerClinic().toList();
        assertEquals(4, ownerClinics.size());
        assertTrue(ownerClinics.stream().anyMatch(ownerClinic -> ownerClinic.getName().equals("Marcos")));
        assertTrue(ownerClinics.stream().anyMatch(ownerClinic -> ownerClinic.getName().equals("Juan")));
        assertTrue(ownerClinics.stream().anyMatch(ownerClinic -> ownerClinic.getName().equals("Mary")));
        assertTrue(ownerClinics.stream().anyMatch(ownerClinic -> ownerClinic.getName().equals("Aitana")));
    }
}
