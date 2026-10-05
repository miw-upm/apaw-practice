package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.adapters.out.carreservation.postgres.ReservationRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.CreationReservation;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.CAR_0;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ReservationServiceIT {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        CreationReservation creation = CreationReservation.builder()
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .destination("Madrid")
                .businessTrip(true)
                .passengerCount(2)
                .userId(user.getId())
                .carId(ID_0)
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Reservation reservation = this.reservationService.create(creation);

        assertThat(reservation.getId()).isNotNull();
        assertThat(reservation.getDate()).isEqualTo(creation.getDate());
        assertThat(reservation.getDurationMinutes()).isEqualTo(120);
        assertThat(reservation.getCar().getId()).isEqualTo(ID_0);
        assertThat(reservation.getUserSnapshot().getId()).isEqualTo(user.getId());

        var entity = this.reservationRepository.findById(reservation.getId()).orElseThrow();
        assertThat(entity.getDestination()).isEqualTo(creation.getDestination());
        assertThat(entity.getCar().getId()).isEqualTo(ID_0);
        assertThat(entity.getUserId()).isEqualTo(user.getId());

        verify(this.userFinder).read(user.getId());
    }

    @Test
    @Transactional
    void testCreateCarNotFoundException() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        UUID nonExistentCarId = UUID.randomUUID();

        CreationReservation creation = CreationReservation.builder()
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .userId(user.getId())
                .carId(nonExistentCarId)
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        assertThatThrownBy(() -> this.reservationService.create(creation))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testFindCarUsageReport() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream()
                    .map(id -> UserSnapshot.builder()
                            .id(id)
                            .firstName("TestUser")
                            .familyName("System")
                            .email("test@email.com")
                            .build())
                    .toList();
        });

        List<CarUsageReport> report = this.reservationService.findCarUsageReport();

        assertThat(report).isNotNull();

        assertThat(report)
                .extracting(CarUsageReport::getTotalDurationMinutes)
                .isSortedAccordingTo(Comparator.nullsLast(Comparator.reverseOrder()));

        assertThat(report).filteredOn(item -> item.getCarRegistration().equals(CAR_0.getLicensePlate()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalReservations()).isGreaterThanOrEqualTo(1L);
                    assertThat(item.getTotalDurationMinutes()).isGreaterThanOrEqualTo(1L);
                    assertThat(item.getUserSnapshot()).isNotNull();
                    assertThat(item.getUserSnapshot().getFirstName()).isEqualTo("TestUser");
                });
    }
}