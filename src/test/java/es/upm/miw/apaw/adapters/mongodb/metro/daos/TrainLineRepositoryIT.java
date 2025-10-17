package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainLineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TrainLineRepositoryIT {

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
    void testFindByNumber() {
        Integer trainLineNumber = 1;

        Optional<TrainLineEntity> trainLine = trainLineRepository.findByNumber(trainLineNumber);

        assertThat(trainLine).isPresent();
        assertThat(trainLine.get().getNumber()).isEqualTo(trainLineNumber);
        assertThat(trainLine.get().getColor()).isEqualTo("Red");
        assertThat(trainLine.get().getNumStations()).isEqualTo(15);
        assertThat(trainLine.get().getCircular()).isFalse();
    }

    @Test
    void testFindByNumberNotFound() {
        Integer nonExistentNumber = 500;

        Optional<TrainLineEntity> trainLine = trainLineRepository.findByNumber(nonExistentNumber);

        assertThat(trainLine).isEmpty();
    }


}