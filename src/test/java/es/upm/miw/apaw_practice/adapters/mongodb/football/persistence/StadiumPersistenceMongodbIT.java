package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.football.FootballSeederService;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class StadiumPersistenceMongodbIT {

    @Autowired
    private StadiumPersistenceMongodb stadiumPersistenceMongodb;

    @Autowired
    private FootballSeederService footballSeederService;

    @Test
    void testUpdate() {
        Stadium stadium = this.stadiumPersistenceMongodb.readByCity("Madrid");
        assertEquals(stadium.getName(), "Bernabeu");
        stadium.setName("Nuevo Bernabeu");
        this.stadiumPersistenceMongodb.update(stadium);
        Stadium stadiumUpdated = this.stadiumPersistenceMongodb.readByCity("Madrid");
        assertEquals(stadiumUpdated.getName(), "Nuevo Bernabeu");
        footballSeederService.deleteAll();
        footballSeederService.seedDatabase();
    }
}
