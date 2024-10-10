package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;
import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ClubRepositoryIT {

    @Autowired
    private ClubRepository clubRepository;

    @Test
    void testFindByName(){
        assertTrue(this.clubRepository.findByName("Kapital").isPresent());
        assertTrue(this.clubRepository.findByName("Cuenca Club").isPresent());
    }

}
