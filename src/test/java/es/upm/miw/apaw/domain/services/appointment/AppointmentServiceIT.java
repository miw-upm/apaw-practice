package es.upm.miw.apaw.domain.services.appointment;

import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentEntity;
import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import es.upm.miw.apaw.domain.model.appointment.AppointmentFindCriteria;
import es.upm.miw.apaw.domain.model.appointment.AppointmentStatus;
import es.upm.miw.apaw.domain.model.appointment.CreationAppointment;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.AppointmentLocationSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.AppointmentLocationSeederForDev.ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentServiceIT {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @MockitoBean
    private UserFinder userFinder;

    private UserSnapshot user() {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
    }

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        CreationAppointment creation = CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user.getId())
                .build();

        Appointment appointment = this.appointmentService.create(creation);

        assertThat(appointment.getId()).isNotNull();
        assertThat(appointment.getCreationDate()).isNotNull();
        assertThat(appointment.getDurationMinutes()).isEqualTo(30);
        assertThat(appointment.getVirtual()).isFalse();
        assertThat(appointment.getStatus()).isEqualTo(AppointmentStatus.SCHEDULED);
        assertThat(appointment.getLocation()).isNull();
        assertThat(appointment.getClient()).isEqualTo(user);
        AppointmentEntity entity = this.appointmentRepository.findById(appointment.getId()).orElseThrow();
        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());
        assertThat(entity.getClientId()).isEqualTo(user.getId());
        assertThat(entity.getLocation()).isNull();
    }

    @Test
    @Transactional
    void testCreateWithLocation() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        CreationAppointment creation = CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user.getId())
                .locationId(ID_0)
                .build();

        Appointment appointment = this.appointmentService.create(creation);

        assertThat(appointment.getId()).isNotNull();
        assertThat(appointment.getLocation()).isNotNull();
        assertThat(appointment.getLocation().getId()).isEqualTo(ID_0);
        AppointmentEntity entity = this.appointmentRepository.findById(appointment.getId()).orElseThrow();
        assertThat(entity.getLocation()).isNotNull();
        assertThat(entity.getLocation().getId()).isEqualTo(ID_0);
    }

    @Test
    void testCreateLocationNotFound() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        UUID unknownLocationId = UUID.randomUUID();
        CreationAppointment creation = CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user.getId())
                .locationId(unknownLocationId)
                .build();

        assertThatThrownBy(() -> this.appointmentService.create(creation))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testCreateUserNotFound() {
        UUID unknownUserId = UUID.randomUUID();
        when(this.userFinder.read(unknownUserId)).thenThrow(new NotFoundException("User id not found: " + unknownUserId));
        CreationAppointment creation = CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(unknownUserId)
                .build();

        assertThatThrownBy(() -> this.appointmentService.create(creation))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testFindCityReport() {
        UserSnapshot user1 = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        UserSnapshot user2 = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600000101")
                .firstName("cliente1")
                .build();
        when(this.userFinder.read(user1.getId())).thenReturn(user1);
        when(this.userFinder.read(user2.getId())).thenReturn(user2);
        // user1: 2 appointments in Madrid (ID_0), user2: 1 appointment in Barcelona (ID_1)
        this.appointmentService.create(creation(user1.getId(), ID_0));
        this.appointmentService.create(creation(user1.getId(), ID_0));
        this.appointmentService.create(creation(user2.getId(), ID_1));
        when(this.userFinder.findByIds(org.mockito.ArgumentMatchers.anySet()))
                .thenReturn(List.of(user1, user2));

        List<AppointmentCityReport> report = this.appointmentService.findCityReport();

        assertThat(report).isNotEmpty();
        AppointmentCityReport first = report.get(0);
        assertThat(first.getTotalAppointments()).isGreaterThanOrEqualTo(2);
        assertThat(first.getClient()).isNotNull();
        assertThat(report).allSatisfy(r -> assertThat(r.getClient()).isNotNull());
        assertThat(report).extracting(AppointmentCityReport::getTotalAppointments)
                .isSortedAccordingTo((a, b) -> Long.compare(b, a));
    }

    @Test
    @Transactional
    void testFindCityReportEmpty() {
        List<AppointmentCityReport> report = this.appointmentService.findCityReport();

        assertThat(report).isEmpty();
    }

    @Test
    @Transactional
    void testFindByStatus() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of(user));
        Appointment created = this.appointmentService.create(CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user.getId())
                .build());

        List<Appointment> scheduled = this.appointmentService.find(
                AppointmentFindCriteria.builder().status(AppointmentStatus.SCHEDULED).build());
        List<Appointment> cancelled = this.appointmentService.find(
                AppointmentFindCriteria.builder().status(AppointmentStatus.CANCELLED).build());

        assertThat(scheduled).extracting(Appointment::getId).contains(created.getId());
        assertThat(cancelled).extracting(Appointment::getId).doesNotContain(created.getId());
    }

    @Test
    @Transactional
    void testFindUpcoming() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of(user));
        Appointment future = this.appointmentService.create(CreationAppointment.builder()
                .title("Future " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user.getId())
                .build());
        AppointmentEntity past = buildEntity(user.getId(), LocalDateTime.now().minusDays(1));
        this.appointmentRepository.save(past);

        List<Appointment> upcoming = this.appointmentService.find(
                AppointmentFindCriteria.builder().upcoming(true).build());
        List<Appointment> notUpcoming = this.appointmentService.find(
                AppointmentFindCriteria.builder().upcoming(false).build());

        assertThat(upcoming).extracting(Appointment::getId)
                .contains(future.getId()).doesNotContain(past.getId());
        assertThat(notUpcoming).extracting(Appointment::getId)
                .contains(past.getId()).doesNotContain(future.getId());
    }

    @Test
    @Transactional
    void testFindByCity() {
        UserSnapshot user = user();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of(user));
        Appointment withMadrid = this.appointmentService.create(creation(user.getId(), ID_0));

        List<Appointment> inMadrid = this.appointmentService.find(
                AppointmentFindCriteria.builder().city("Madrid").build());
        List<Appointment> inBarcelona = this.appointmentService.find(
                AppointmentFindCriteria.builder().city("Barcelona").build());

        assertThat(inMadrid).extracting(Appointment::getId).contains(withMadrid.getId());
        assertThat(inBarcelona).extracting(Appointment::getId).doesNotContain(withMadrid.getId());
    }

    @Test
    @Transactional
    void testFindByClientMobile() {
        UserSnapshot user1 = user();
        UserSnapshot user2 = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600000101")
                .firstName("cliente1")
                .build();
        when(this.userFinder.read(user1.getId())).thenReturn(user1);
        when(this.userFinder.read(user2.getId())).thenReturn(user2);
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of(user1, user2));
        Appointment appt1 = this.appointmentService.create(CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user1.getId())
                .build());
        Appointment appt2 = this.appointmentService.create(CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(user2.getId())
                .build());

        List<Appointment> result = this.appointmentService.find(
                AppointmentFindCriteria.builder().clientMobile(user1.getMobile()).build());

        assertThat(result).extracting(Appointment::getId)
                .contains(appt1.getId()).doesNotContain(appt2.getId());
        assertThat(result).allSatisfy(a ->
                assertThat(a.getClient().getMobile()).isEqualTo(user1.getMobile()));
    }

    private CreationAppointment creation(UUID userId, UUID locationId) {
        return CreationAppointment.builder()
                .title("Appointment " + UUID.randomUUID())
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .userId(userId)
                .locationId(locationId)
                .build();
    }

    private AppointmentEntity buildEntity(UUID clientId, LocalDateTime scheduledDate) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle("Appointment " + UUID.randomUUID());
        entity.setScheduledDate(scheduledDate);
        entity.setDurationMinutes(30);
        entity.setCreationDate(LocalDateTime.now());
        entity.setVirtual(false);
        entity.setStatus(AppointmentStatus.SCHEDULED);
        entity.setClientId(clientId);
        return entity;
    }
}
