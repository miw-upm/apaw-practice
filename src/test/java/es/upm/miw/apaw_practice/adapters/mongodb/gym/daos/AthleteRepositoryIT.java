package es.upm.miw.apaw_practice.adapters.mongodb.gym.daos;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class AthleteRepositoryIT {
    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    public void testFindByNie() {
        assertTrue(this.athleteRepository.findByNie("12345678a").isPresent());
        AthleteEntity athlete = this.athleteRepository.findByNie("12345678a").get();
        assertEquals("ada", athlete.getName());
        assertEquals("perez", athlete.getFamilyName());


    }
}
