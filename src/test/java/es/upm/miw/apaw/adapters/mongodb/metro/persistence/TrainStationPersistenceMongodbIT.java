package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.MetroSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TrainStationPersistenceMongodbIT {
    @Autowired
    private TrainStationPersistenceMongodb trainStationPersistenceMongodb;

    @Autowired
    private MetroSeeder metroSeeder;

    @BeforeEach
    void resetDb() {
        metroSeeder.deleteAll();
        metroSeeder.seedDatabase();
    }

    @Test
    void testReadCapacityByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.trainStationPersistenceMongodb.readCapacityByName("Not Exist Station"));
    }

    @Test
    void testReadCapacityByName(){
        assertThat(this.trainStationPersistenceMongodb.readCapacityByName("Central Station")).isEqualTo(500);
    }
}