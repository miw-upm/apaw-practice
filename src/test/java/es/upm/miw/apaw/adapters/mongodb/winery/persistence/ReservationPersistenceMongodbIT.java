package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.WinerySeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationPersistenceMongodbIT {

    @Autowired
    private ReservationPersistenceMongodb reservationPersistenceMongodb;

    @Autowired
    private WinerySeeder winerySeeder;

    @Test
    void testCreate() {
        UUID tastingSessionId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101");

        TastingSession tastingSession = TastingSession.builder()
                .id(tastingSessionId)
                .wines(List.of())
                .evaluations(List.of())
                .build();

        Reservation reservation = Reservation.builder().id(UUID.randomUUID())
                .user(UserDto.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .firstName("Test")
                        .mobile("123456789")
                        .build())
                .tastingSession(tastingSession)
                .totalCost(new BigDecimal("50.00"))
                .confirmed(true)
                .build();

        Reservation reservationDb = this.reservationPersistenceMongodb.create(reservation);

        assertThat(reservationDb).isNotNull();
        assertThat(reservationDb.getId()).isNotNull();
        assertThat(reservationDb.getTotalCost()).isEqualByComparingTo("50.00");
        assertThat(reservationDb.getConfirmed()).isTrue();
        assertThat(reservationDb.getUser().getId())
                .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"));
        assertThat(reservationDb.getTastingSession()).isNotNull();
        assertThat(reservationDb.getTastingSession().getId()).isEqualTo(tastingSessionId);
    }

}
