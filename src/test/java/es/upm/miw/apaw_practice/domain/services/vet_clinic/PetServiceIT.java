package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class PetServiceIT {

    @Autowired
    private PetService petService;
    @Autowired
    private PetPersistence petPersistence;

    @Test
    void deleteTest() {
        this.petService.delete("Lilith", "Pedro");
        assertThrows(NotFoundException.class, () ->
                this.petPersistence.readByNickAndOwner("Lilith", "Pedro"));
    }

}
