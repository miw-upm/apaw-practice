package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentPersistenceMongodbIT {

    @Autowired
    private AppointmentPersistenceMongodb appointmentPersistence;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindById_ok() {
        Appointment saved = getSavedAppointment();
        Optional<Appointment> appointment = this.appointmentPersistence.findById(saved.getId());
        assertThat(appointment).isPresent();
        assertThat(appointment.get().getReason()).isEqualTo("Vaccination");
    }

    @Test
    void testFindById_notFound() {
        Optional<Appointment> appointment = this.appointmentPersistence.findById(UUID.randomUUID());
        assertThat(appointment).isEmpty();
    }

    @Test
    void testSave_ok() {
        Appointment saved = getSavedAppointment();
        assertThat(saved.getReason()).isEqualTo("Vaccination");
    }

    private Appointment getSavedAppointment() {
        Appointment newAppointment = Appointment.builder()
                .id(UUID.randomUUID())
                .reason("Vaccination")
                .build();
        return this.appointmentPersistence.save(newAppointment);
    }
}