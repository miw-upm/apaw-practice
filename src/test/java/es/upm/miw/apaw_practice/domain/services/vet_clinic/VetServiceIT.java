package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.VetPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
public class VetServiceIT {

    @Autowired
    private VetService vetService;
    @Autowired
    private VetPersistence vetPersistence;

    @Test
    void createTest() {
        Vet vet = Vet.builder()
                .vetNumber(888)
                .name("vet8")
                .surname("surname8")
                .build();
        this.vetService.create(vet);
        Vet persistedVet = this.vetPersistence.readByVetNumber(888);
        assertEquals(vet.getVetNumber(), persistedVet.getVetNumber());
        assertEquals(vet.getName(), persistedVet.getName());
        assertEquals(vet.getSurname(), persistedVet.getSurname());
    }
}
