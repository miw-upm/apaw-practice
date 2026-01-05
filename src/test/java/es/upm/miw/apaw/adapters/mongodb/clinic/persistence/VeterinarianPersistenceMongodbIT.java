package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
}