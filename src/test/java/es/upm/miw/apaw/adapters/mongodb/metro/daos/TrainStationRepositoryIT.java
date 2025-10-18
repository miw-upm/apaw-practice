package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainStationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class TrainStationRepositoryIT {

    @Autowired
    private TrainStationRepository trainStationRepository;

    @Autowired
    private MetroSeeder metroSeeder;

    @BeforeEach
    void resetDb() {
        metroSeeder.deleteAll();
        metroSeeder.seedDatabase();
    }

    @Test
    void testFindByName() {
        assertTrue(this.trainStationRepository.findByName("Central Station").isPresent());
        TrainStationEntity trainStation = this.trainStationRepository.findByName("Central Station").get();
        assertThat(trainStation.getCapacity()).isEqualTo(500);
        assertThat(trainStation.getLocation()).isEqualTo("Downtown");
        assertThat(trainStation.getMultipleLines()).isTrue();
        assertThat(trainStation.getInaugurationDate()).isEqualTo(LocalDate.of(1990,5,12));
    }
}