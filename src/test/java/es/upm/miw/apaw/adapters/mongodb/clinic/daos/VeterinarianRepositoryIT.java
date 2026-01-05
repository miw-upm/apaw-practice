package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class VeterinarianRepositoryIT {

    @Autowired
    private VeterinarianRepository veterinarianRepository;
    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testFindByLicenseNumber() {
        Optional<VeterinarianEntity> vet = this.veterinarianRepository.findByLicenseNumber(ClinicSeeder.LICENSE_DR_JONES);
        assertTrue(vet.isPresent());
        assertThat(vet.get().getLicenseNumber()).isEqualTo(ClinicSeeder.LICENSE_DR_JONES);
        assertThat(vet.get().getUserId()).isEqualTo(ClinicSeeder.USER_UUID_DR_JONES);
    }
}