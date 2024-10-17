package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
class CompetitorPersistenceMongodbIT {

    @Autowired
    private CompetitorPersistenceMongodb competitorPersistenceMongodb;

    @Test
    void readByLicence() {
        Optional<Competitor> competitor = this.competitorPersistenceMongodb.readAll()
                .filter(competitorFilter -> "WU/A/00126".equals(competitorFilter.getLicence()))
                .findFirst();
        assertTrue(competitor.isPresent());
    }
}
