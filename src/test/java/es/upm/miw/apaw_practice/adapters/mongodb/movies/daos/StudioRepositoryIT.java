package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.StudioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class StudioRepositoryIT {

    @Autowired
    private StudioRepository studioRepository;

    @Test
    void testFindByNameNotExists(){
        assertTrue(studioRepository.findByName("Paramount Pictures").isEmpty());
    }

    @Test
    void testFindByName() {
        assertTrue(this.studioRepository.findByName("Warner Bros").isPresent());
        StudioEntity studio = this.studioRepository.findByName("Warner Bros").get();
        assertEquals(4, studio.getProducedMovies().size());
        assertEquals(10000000000L, studio.getMarketCapitalization().longValue());
    }
}
