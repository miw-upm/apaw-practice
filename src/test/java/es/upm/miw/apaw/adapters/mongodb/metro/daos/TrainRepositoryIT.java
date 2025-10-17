package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class TrainRepositoryIT {

    @Autowired
    private TrainRepository trainRepository;

    @Test
    void testDeleteTrainById() {
        assertThat(this.trainRepository.deleteTrainById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0203"))).isEqualTo(1);
    }
}