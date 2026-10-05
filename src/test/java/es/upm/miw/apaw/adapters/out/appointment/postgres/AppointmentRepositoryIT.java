package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.model.appointment.AppointmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentRepositoryIT {

    @Autowired
    private AppointmentLocationRepository appointmentLocationRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @Transactional
    void testFindCityReport() {
        AppointmentLocationEntity location = this.appointmentLocationRepository.saveAndFlush(
                new AppointmentLocationEntity(AppointmentLocation.builder()
                        .id(UUID.randomUUID()).name("Sala Report " + UUID.randomUUID())
                        .city("Sevilla").creationDate(LocalDateTime.now()).build()));
        UUID clientId = UUID.randomUUID();
        this.appointmentRepository.saveAndFlush(AppointmentEntity.builder()
                .id(UUID.randomUUID()).title("City report test")
                .scheduledDate(LocalDateTime.now().plusDays(1))
                .durationMinutes(30).creationDate(LocalDateTime.now())
                .virtual(false).status(AppointmentStatus.SCHEDULED)
                .location(location).clientId(clientId).build());

        List<AppointmentCityReport> report = this.appointmentRepository.findCityReport();

        assertThat(report).extracting(AppointmentCityReport::getTotalAppointments)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).anyMatch(r ->
                r.getClientId().equals(clientId)
                && r.getCity().equals("Sevilla")
                && r.getTotalAppointments() == 1L);
    }
}
