package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.CAR_0;
import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.CAR_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ReservationRepositoryIT {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testFindRawCarUsageReport() {
        List<CarUsageRawReport> report = this.reservationRepository.findRawCarUsageReport();

        assertThat(report)
                .extracting(CarUsageRawReport::getTotalDurationMinutes)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(report).filteredOn(item -> item.getCarRegistration().equals(CAR_0.getLicensePlate()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getUserId()).isNotNull();
                    assertThat(item.getTotalReservations()).isGreaterThanOrEqualTo(1L);
                    assertThat(item.getTotalDurationMinutes()).isGreaterThanOrEqualTo(1L);
                });

        assertThat(report).filteredOn(item -> item.getCarRegistration().equals(CAR_1.getLicensePlate()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getUserId()).isNotNull();
                    assertThat(item.getTotalReservations()).isGreaterThanOrEqualTo(1L);
                });
    }
}