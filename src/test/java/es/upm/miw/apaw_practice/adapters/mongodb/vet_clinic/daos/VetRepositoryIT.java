package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VetRepositoryIT {
    @Autowired
    private VetRepository vetRepository;

    @Test
    void findVetByVetNumberTest(){
        assertTrue(this.vetRepository.findVetByVetNumber(111).isPresent());
        VetEntity vet = this.vetRepository.findVetByVetNumber(111).get();
        assertEquals("vet1", vet.getName());
        assertEquals("surname1", vet.getSurname());
        assertEquals(1, vet.getAppointmentEntities().size());
    }

}
