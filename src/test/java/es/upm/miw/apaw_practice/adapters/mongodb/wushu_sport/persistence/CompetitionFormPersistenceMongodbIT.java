package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


@TestConfig
class CompetitionFormPersistenceMongodbIT {

    @Autowired
    CompetitionFormPersistenceMongodb competitionFormPersistenceMongodb;

    @Test
    void testCreateAndReadAll(){
        CompetitionForm competitionForm = new CompetitionForm(8.00, Duration.ofMinutes(1).plusSeconds(20), "changquan");
        this.competitionFormPersistenceMongodb.create(competitionForm);
        Stream<CompetitionForm> competitionFormsBD = this.competitionFormPersistenceMongodb.readAll();
        assertEquals( 6, competitionFormsBD.count());
    }

}
