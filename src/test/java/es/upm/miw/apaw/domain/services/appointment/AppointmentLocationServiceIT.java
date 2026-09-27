package es.upm.miw.apaw.domain.services.appointment;

import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentEntity;
import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentLocationRepository;
import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocationPatch;
import es.upm.miw.apaw.domain.model.appointment.AppointmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.AppointmentLocationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentLocationServiceIT {

    @Autowired
    private AppointmentLocationService appointmentLocationService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentLocationRepository appointmentLocationRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.appointmentLocationService.read(ID_0))
                .usingRecursiveComparison().isEqualTo(LOCATION_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.appointmentLocationService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalLocations() {
        AppointmentLocation extra = this.createLocation();
        List<AppointmentLocation> locations = this.appointmentLocationService.findAll();
        assertThat(locations).extracting(AppointmentLocation::getId)
                .contains(ID_0, ID_1, ID_2, extra.getId());
        assertThat(locations).extracting(AppointmentLocation::getName)
                .containsSubsequence(LOCATION_2.getName(), LOCATION_1.getName(), LOCATION_0.getName());
    }

    @Test
    void testCreate() {
        AppointmentLocation location = this.createLocation();
        AppointmentLocation stored = this.appointmentLocationService.read(location.getId());
        assertThat(stored).usingRecursiveComparison().ignoringFields("creationDate").isEqualTo(location);
        assertThat(stored.getCreationDate()).isNotNull();
    }

    @Test
    void testCreateDuplicateName() {
        AppointmentLocation location = AppointmentLocation.builder().name(LOCATION_0.getName())
                .city("Madrid").build();
        assertThatThrownBy(() -> this.appointmentLocationService.create(location))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LOCATION_0.getName());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        AppointmentLocation original = this.createLocation();
        AppointmentLocation replacement = AppointmentLocation.builder()
                .name("Updated " + UUID.randomUUID()).city("Sevilla").floor(5).build();
        this.appointmentLocationService.update(original.getId(), replacement);
        AppointmentLocation updated = this.appointmentLocationService.read(original.getId());
        assertThat(updated.getName()).isEqualTo(replacement.getName());
        assertThat(updated.getCity()).isEqualTo("Sevilla");
        assertThat(updated.getFloor()).isEqualTo(5);
        assertThat(updated.getAddress()).isNull();
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateSameName() {
        AppointmentLocation location = this.createLocation();
        location.setCity("Valencia");
        this.appointmentLocationService.update(location.getId(), location);
        assertThat(this.appointmentLocationService.read(location.getId()).getCity()).isEqualTo("Valencia");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.appointmentLocationService.update(id, LOCATION_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateNameLeavesLocationUnchanged() {
        AppointmentLocation location = this.createLocation();
        AppointmentLocation duplicate = AppointmentLocation.builder()
                .name(LOCATION_0.getName()).city("Madrid").build();
        assertThatThrownBy(() -> this.appointmentLocationService.update(location.getId(), duplicate))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LOCATION_0.getName());
        assertThat(this.appointmentLocationService.read(location.getId()).getName())
                .isEqualTo(location.getName());
    }

    @Test
    void testDelete() {
        AppointmentLocation location = this.createLocation();
        this.appointmentLocationService.delete(location.getId());
        assertThatThrownBy(() -> this.appointmentLocationService.read(location.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedLocation() {
        AppointmentLocation location = this.createLocation();
        AppointmentEntity appointment = AppointmentEntity.builder()
                .id(UUID.randomUUID())
                .title("Referenced appointment")
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .durationMinutes(30)
                .creationDate(LocalDateTime.now())
                .virtual(false)
                .status(AppointmentStatus.SCHEDULED)
                .location(this.appointmentLocationRepository.getReferenceById(location.getId()))
                .clientId(UUID.randomUUID())
                .build();
        this.appointmentRepository.saveAndFlush(appointment);
        assertThatThrownBy(() -> this.appointmentLocationService.delete(location.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(location.getId().toString());
    }

    @Test
    void testPatchUpdatesOnlyPresentFields() {
        AppointmentLocation location = this.createLocation();
        String originalName = location.getName();
        this.appointmentLocationService.patch(location.getId(),
                new AppointmentLocationPatch(null, null, "Bilbao", null, null, 7));
        AppointmentLocation updated = this.appointmentLocationService.read(location.getId());
        assertThat(updated.getName()).isEqualTo(originalName);
        assertThat(updated.getCity()).isEqualTo("Bilbao");
        assertThat(updated.getFloor()).isEqualTo(7);
    }

    @Test
    void testPatchDuplicateName() {
        AppointmentLocation location = this.createLocation();
        assertThatThrownBy(() -> this.appointmentLocationService.patch(location.getId(),
                new AppointmentLocationPatch(LOCATION_0.getName(), null, null, null, null, null)))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LOCATION_0.getName());
        assertThat(this.appointmentLocationService.read(location.getId()).getName())
                .isEqualTo(location.getName());
    }

    private AppointmentLocation createLocation() {
        return this.appointmentLocationService.create(AppointmentLocation.builder()
                .name("IT location " + UUID.randomUUID()).city("Madrid").build());
    }
}
