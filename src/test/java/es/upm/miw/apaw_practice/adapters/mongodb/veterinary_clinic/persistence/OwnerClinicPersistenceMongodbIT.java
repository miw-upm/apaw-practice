package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OwnerClinicPersistenceMongodbIT {

    @Autowired
    private OwnerClinicPersistenceMongodb ownerPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.ownerPersistenceMongodb.read("Cris"));
    }

    @Test
    void testNameNotExist(){
        assertFalse(this.ownerPersistenceMongodb.existName("Paul"));
    }

    @Test
    void testNameExist() {
        assertTrue(this.ownerPersistenceMongodb.existName("Marcos"));
    }

    @Test
    void testCreatAndRead() {
        Owner owner = new Owner("James", "Street California", "584794235");
        this.ownerPersistenceMongodb.create(owner);
        Owner ownerBD = this.ownerPersistenceMongodb.read("James");
        assertEquals("James", ownerBD.getName());
        assertEquals("Street California", ownerBD.getAddress());
        assertEquals("584794235", ownerBD.getPhone());
    }

    @Test
    void testCreateAndUpdate() {
        Owner ownerCreation =
                new Owner("Lion", "Street Principal", "333444555");
        Owner ownerBD = this.ownerPersistenceMongodb.create(ownerCreation);
        ownerBD.setAddress("Street Oviedo");
        ownerBD.setPhone("444555222");
        this.ownerPersistenceMongodb.update("Street Oviedo", "444555222", ownerBD);
        ownerBD = this.ownerPersistenceMongodb.read("Lion");
        assertEquals("Street Oviedo", ownerBD.getAddress());
        assertEquals("444555222", ownerBD.getPhone());
    }
}