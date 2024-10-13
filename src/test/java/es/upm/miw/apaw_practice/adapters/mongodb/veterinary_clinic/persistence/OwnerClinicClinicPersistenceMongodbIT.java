package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class OwnerClinicClinicPersistenceMongodbIT {

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
        OwnerClinic ownerClinic = new OwnerClinic("James", "Street California", "584794235");
        this.ownerPersistenceMongodb.create(ownerClinic);
        OwnerClinic ownerClinicBD = this.ownerPersistenceMongodb.read("James");
        assertEquals("James", ownerClinicBD.getName());
        assertEquals("Street California", ownerClinicBD.getAddress());
        assertEquals("584794235", ownerClinicBD.getPhone());
    }

    @Test
    void testCreateAndUpdate() {
        OwnerClinic ownerClinicCreation =
                new OwnerClinic("Lion", "Street Principal", "333444555");
        OwnerClinic ownerClinicBD = this.ownerPersistenceMongodb.create(ownerClinicCreation);
        ownerClinicBD.setAddress("Street Oviedo");
        ownerClinicBD.setPhone("444555222");
        this.ownerPersistenceMongodb.update("Street Oviedo", "444555222", ownerClinicBD);
        ownerClinicBD = this.ownerPersistenceMongodb.read("Lion");
        assertEquals("Street Oviedo", ownerClinicBD.getAddress());
        assertEquals("444555222", ownerClinicBD.getPhone());
    }
}