package es.upm.miw.apaw.functionaltests.carreservation;

import es.upm.miw.apaw.adapters.in.carreservation.ReservationResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Collection;
import java.util.Comparator;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.CAR_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReservationResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testFindCarUsageReport() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Collection<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream()
                    .map(id -> UserSnapshot.builder()
                            .id(id)
                            .firstName("Alice")
                            .familyName("Smith")
                            .email("alice.smith@email.com")
                            .mobile("600000001")
                            .build())
                    .toList();
        });

        this.restTestClient.get().uri(ReservationResource.RESERVATIONS + ReservationResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CarUsageReport[].class)
                .value(reports -> {
                    assertThat(reports).isNotNull();

                    assertThat(reports)
                            .extracting(CarUsageReport::getTotalDurationMinutes)
                            .isSortedAccordingTo(Comparator.reverseOrder());

                    assertThat(reports).filteredOn(report -> report.getCarRegistration() != null
                                    && report.getCarRegistration().equals(CAR_0.getLicensePlate()))
                            .singleElement()
                            .satisfies(report -> {
                                assertThat(report.getTotalReservations()).isGreaterThanOrEqualTo(1L);
                                assertThat(report.getTotalDurationMinutes()).isGreaterThanOrEqualTo(1L);
                                assertThat(report.getUserSnapshot()).isNotNull();
                                assertThat(report.getUserSnapshot().getFirstName()).isEqualTo("Alice");
                            });
                });
    }
}