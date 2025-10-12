package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.persistenceports.airport.BoardingGatePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class BoardingGateServiceIT {

    @Autowired
    private BoardingGateService boardingGateService;

    @Autowired
    private BoardingGatePersistence boardingGatePersistence;

    @Test
    void testUpdate() {
        assertThat(this.boardingGatePersistence.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))).isNotNull();
        assertThat(this.boardingGatePersistence.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).getOpened()).isFalse();
        this.boardingGateService.updateOpened(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"), true);
        assertThat(this.boardingGatePersistence.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).getOpened()).isTrue();
    }
}
