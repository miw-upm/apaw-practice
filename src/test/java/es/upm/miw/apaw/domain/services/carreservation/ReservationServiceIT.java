package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.adapters.out.carreservation.postgres.ReservationRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.CreationReservation;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.model.carreservation.ReservationFindCriteria;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ReservationServiceIT {

    private static final UUID SEED_USER_1 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream()
                    .map(id -> UserSnapshot.builder()
                            .id(id)
                            .firstName("TestUser")
                            .city("Madrid")
                            .build())
                    .toList();
        });
    }

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

    @Test
    void testFindByCriteriaAllNullReturnsAll() {
        ReservationFindCriteria criteria = ReservationFindCriteria.builder().build();
        List<Reservation> reservations = this.reservationService.find(criteria);

        assertThat(reservations).isNotNull().hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testFindByCriteriaFilterByEntityAttributeAndDerivedAndRelation() {
        ReservationFindCriteria criteria = ReservationFindCriteria.builder()
                .durationMinutes(90)
                .carLicensePlate("1234BBB")
                .build();

        List<Reservation> reservations = this.reservationService.find(criteria);

        assertThat(reservations)
                .isNotEmpty()
                .allSatisfy(r -> {
                    assertThat(r.getDurationMinutes()).isEqualTo(90);
                    assertThat(r.getCar()).isNotNull();
                    assertThat(r.getCar().getLicensePlate()).isEqualTo("1234BBB");
                });
    }

    @Test
    void testFindByCriteriaFilterByUserCityWithMock() {
        ReservationFindCriteria criteriaMatch = ReservationFindCriteria.builder()
                .userCity("Madrid")
                .build();

        List<Reservation> matchingReservations = this.reservationService.find(criteriaMatch);

        assertThat(matchingReservations)
                .isNotEmpty()
                .allSatisfy(r -> assertThat(r.getUserSnapshot().getCity()).isEqualToIgnoringCase("Madrid"));

        ReservationFindCriteria criteriaNoMatch = ReservationFindCriteria.builder()
                .userCity("Sevilla")
                .build();

        List<Reservation> noMatchingReservations = this.reservationService.find(criteriaNoMatch);

        assertThat(noMatchingReservations).isEmpty();
    }

    @Test
    @Transactional
    void testFindByUserCityDynamicCreation() {
        UserSnapshot firstUser = UserSnapshot.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .city("Granada")
                .firstName("cliente0")
                .build();

        UserSnapshot secondUser = UserSnapshot.builder()
                .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .city("Valencia")
                .firstName("cliente1")
                .build();

        when(this.userFinder.read(firstUser.getId())).thenReturn(firstUser);
        when(this.userFinder.read(secondUser.getId())).thenReturn(secondUser);

        CreationReservation creationFirst = CreationReservation.builder()
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(11, 30))
                .destination("Granada")
                .businessTrip(true)
                .passengerCount(1)
                .carId(ID_0)
                .userId(firstUser.getId())
                .build();

        CreationReservation creationSecond = CreationReservation.builder()
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(11, 30))
                .destination("Valencia")
                .businessTrip(false)
                .passengerCount(2)
                .carId(ID_0)
                .userId(secondUser.getId())
                .build();

        Reservation first = this.reservationService.create(creationFirst);
        Reservation second = this.reservationService.create(creationSecond);

        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream()
                    .map(id -> {
                        if (id.equals(firstUser.getId())) return firstUser;
                        if (id.equals(secondUser.getId())) return secondUser;
                        return UserSnapshot.builder().id(id).city("Madrid").build();
                    })
                    .toList();
        });

        List<Reservation> reservations = this.reservationService.find(
                ReservationFindCriteria.builder()
                        .userCity("Granada")
                        .build()
        );

        assertThat(reservations).extracting(Reservation::getId)
                .contains(first.getId())
                .doesNotContain(second.getId());

        assertThat(reservations).filteredOn(reservation -> reservation.getId().equals(first.getId()))
                .singleElement()
                .extracting(Reservation::getUserSnapshot)
                .isEqualTo(firstUser);
    }

    @Test
    void testFindSeederWithAllCriteria() {
        ReservationFindCriteria criteria = ReservationFindCriteria.builder()
                .durationMinutes(90)
                .carLicensePlate("1234BBB")
                .userCity("Madrid")
                .build();

        List<Reservation> reservations = this.reservationService.find(criteria);

        assertThat(reservations)
                .isNotEmpty()
                .allSatisfy(r -> {
                    assertThat(r.getDurationMinutes()).isEqualTo(90);
                    assertThat(r.getCar()).isNotNull();
                    assertThat(r.getCar().getLicensePlate()).isEqualTo("1234BBB");
                    assertThat(r.getUserSnapshot().getId()).isEqualTo(SEED_USER_1);
                    assertThat(r.getUserSnapshot().getCity()).isEqualTo("Madrid");
                });
    }
}