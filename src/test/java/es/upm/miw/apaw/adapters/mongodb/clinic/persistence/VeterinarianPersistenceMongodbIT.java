package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class VeterinarianPersistenceMongodbIT {

    @Autowired
    private VeterinarianPersistenceMongodb veterinarianPersistence;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindByLicense_ok() {
        Long license = ClinicSeeder.LICENSE_DR_SMITH;
        Optional<Veterinarian> vet = this.veterinarianPersistence.findByLicenseNumber(license);
        assertThat(vet).isPresent();
        assertThat(vet.get().getLicenseNumber()).isEqualTo(license);
    }

    @Test
    void testFindByLicense_notFound() {
        Optional<Veterinarian> vet = this.veterinarianPersistence.findByLicenseNumber(999999L);
        assertThat(vet).isEmpty();
    }

    @Test
    void testDelete_ok() {
        Long license = ClinicSeeder.LICENSE_DR_JONES;
        Veterinarian vet = this.veterinarianPersistence.findByLicenseNumber(license).orElseThrow();
        this.veterinarianPersistence.delete(vet);
        assertThat(this.veterinarianPersistence.findByLicenseNumber(license)).isEmpty();
    }

    @Test
    void testDelete_notFound() {
        Veterinarian vet = Veterinarian.builder().licenseNumber(999999L).build();
        assertThrows(NotFoundException.class, () -> this.veterinarianPersistence.delete(vet));
    }

    @Test
    void testAddAppointments_ok() {
        Long license = ClinicSeeder.LICENSE_DR_SMITH;
        UUID appointmentId = UUID.randomUUID();

        this.veterinarianPersistence.addAppointments(license, appointmentId);

        Veterinarian vet = this.veterinarianPersistence.findByLicenseNumber(license).orElseThrow();
        assertThat(vet.getAppointments()).isNotNull();
        assertThat(vet.getAppointments().stream().map(Appointment::getId)).contains(appointmentId);
    }

    @Test
    void testAddAppointments_notFound() {
        UUID appointmentId = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.veterinarianPersistence.addAppointments(999999L, appointmentId));
    }

    @Test
    void testFindByAppointmentIds() {
        List<Veterinarian> veterinarians = this.veterinarianPersistence.findByAppointmentIds(
                List.of(ClinicSeeder.ID_APPOINTMENT_GRIPE, ClinicSeeder.ID_APPOINTMENT_REVISION));
        assertThat(veterinarians).isNotEmpty();
        assertThat(veterinarians).size().isEqualTo(2);
    }

}