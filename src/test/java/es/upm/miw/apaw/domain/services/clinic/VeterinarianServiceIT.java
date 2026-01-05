package es.upm.miw.apaw.domain.services.clinic;

import static org.assertj.core.api.Assertions.assertThat;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class VeterinarianServiceIT {

    @Autowired
    private VeterinarianService veterinarianService;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testReadByLicense() {
        Veterinarian veterinarian = this.veterinarianService.readByLicense(ClinicSeeder.LICENSE_DR_SMITH);
        assertThat(veterinarian.getLicenseNumber()).isEqualTo(ClinicSeeder.LICENSE_DR_SMITH);
    }
}