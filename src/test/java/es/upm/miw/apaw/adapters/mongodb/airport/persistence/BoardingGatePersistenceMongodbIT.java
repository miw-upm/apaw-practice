package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class BoardingGatePersistenceMongodbIT {

    @Autowired
    private BoardingGatePersistenceMongodb boardingGatePersistence;

    @Test
    void testUpdateOpenedAndFindById() {
        assertThat(this.boardingGatePersistence.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))).isNotNull();
        BoardingGate boardingGate = this.boardingGatePersistence.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
        assertThat(boardingGate.getOpened()).isTrue();
        boardingGate.setOpened(false);
        BoardingGate updatedBoardingGate = this.boardingGatePersistence.update(boardingGate);
        assertThat(updatedBoardingGate.getOpened()).isFalse();
    }
}
