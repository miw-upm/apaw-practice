package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.MetroSeeder;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainLineRepository;
import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainLineEntity;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TrainLinePersistenceMongodbIT {
    @Autowired
    private TrainLinePersistenceMongodb trainLinePersistenceMongodb;

    @Autowired
    private TrainLineRepository trainLineRepository;

    @Autowired
    private MetroSeeder metroSeeder;

    @BeforeEach
    void resetDb() {
        metroSeeder.deleteAll();
        metroSeeder.seedDatabase();
    }

    @Test
    void testCreate() {
        TrainLine trainLine = TrainLine.builder()
                .number(7)
                .color("Maroon")
                .numStations(25)
                .circular(false)
                .trains(Collections.emptyList())
                .build();

        TrainLine createdTrainLine = trainLinePersistenceMongodb.create(trainLine);

        assertThat(createdTrainLine).isNotNull();
        assertThat(createdTrainLine.getNumber()).isEqualTo(7);
        assertThat(createdTrainLine.getColor()).isEqualTo("Maroon");
        assertThat(createdTrainLine.getNumStations()).isEqualTo(25);
        assertThat(createdTrainLine.getCircular()).isFalse();
        assertThat(createdTrainLine.getTrains())
                .isNotNull()
                .isEmpty();


        Optional<TrainLineEntity> trainLineEntity = trainLineRepository.findByNumber(7);
        assertThat(trainLineEntity).isPresent();
        assertThat(trainLineEntity.get().getNumber()).isEqualTo(7);
    }

    @Test
    void testExistNumberTrue() {
        boolean trainLine = trainLinePersistenceMongodb.existNumber(1);
        assertThat(trainLine).isTrue();
    }

    @Test
    void testExistNameFalse() {
        boolean trainLine = trainLinePersistenceMongodb.existNumber(404);
        assertThat(trainLine).isFalse();
    }
}