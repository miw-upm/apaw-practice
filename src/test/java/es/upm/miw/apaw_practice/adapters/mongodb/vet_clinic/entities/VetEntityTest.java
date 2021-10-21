package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class VetEntityTest {

    @Test
    void vetToEntityAndViceversaTest(){
        Vet vet = Vet.builder()
                .vetNumber(9797)
                .name("vet97")
                .surname("surname97")
                .build();
        List<AppointmentEntity> appointments = List.of();
        VetEntity vetEntity = new VetEntity(vet, appointments);
        assertNotNull(vetEntity.getId());
        assertEquals(9797, vetEntity.getVetNumber());
        assertEquals("vet97", vetEntity.getName());
        assertEquals("surname97", vetEntity.getSurname());
        assertEquals(vet.getClass(), vetEntity.toVet().getClass());
    }
}
