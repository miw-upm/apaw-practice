package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.movies.Award;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.AwardPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class AwardPersistenceMongodbIT {

    @Autowired
    private AwardPersistence awardPersistence;

    @Test
    void testCreateAndExists() {
        Award award = new Award("NewAward", "Oscar", "Best Cinematography", LocalDate.of(2024, 3, 15));
        awardPersistence.create(award);
        assertTrue(awardPersistence.existsByName("NewAward"));
    }

    @Test
    void testDelete() {
        String name = "Oscar_2020_BestDirector";
        assertTrue(awardPersistence.existsByName(name));
        awardPersistence.delete(name);
        assertFalse(awardPersistence.existsByName(name));
    }
}
