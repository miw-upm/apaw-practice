package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainEntity;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TrainPersistenceMongodbIT {
    @Autowired
    private TrainPersistenceMongodb trainPersistenceMongodb;

    @Autowired
    private TrainRepository trainRepository;


    @Test
    void testDelete() {
        UUID trainId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200");

        Optional<TrainEntity> trainBefore = trainRepository.findById(trainId);
        assertThat(trainBefore).isPresent(); // Ensure it exists before deletion

        // When: deleting the train
        trainPersistenceMongodb.delete(trainId);

        // Then: the train should no longer exist
        Optional<TrainEntity> trainAfter = trainRepository.findById(trainId);
        assertThat(trainAfter).isEmpty();
    }

    @Test
    void testDeleteNonExistent() {
        UUID trainId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        // 1111... is a train that does not exist.

        Optional<TrainEntity> trainBefore = trainRepository.findById(trainId);
        assertThat(trainBefore).isEmpty();

        trainPersistenceMongodb.delete(trainId);

        Optional<TrainEntity> trainAfter = trainRepository.findById(trainId);
        assertThat(trainAfter).isEmpty();
    }
}