package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CompetitionPersistenceMongodbIT {

    @Autowired
    private CompetitionPersistenceMongodb competitionPersistenceMongodb;

    @Test
    void testReadById() {
        Optional<Competition> competition = this.competitionPersistenceMongodb.readAll()
                .filter(comp -> "Champions League".equals(comp.getNameCompetition()))
                .findFirst();

        assertTrue(competition.isPresent());
    }
}
