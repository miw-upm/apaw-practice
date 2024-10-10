package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@TestConfig
class ClubPersistenceMongodbIT {
    @Autowired
    private ClubPersistenceMongodb clubPersistence;
    @Test
    void testReadAll() {
        List<Club> clubs = this.clubPersistence.readAll().toList();
        assertNotNull(clubs);
        assertFalse(clubs.isEmpty());
    }
}
