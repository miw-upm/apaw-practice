package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.adapters.resources.clinic.VeterinarianResource;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class VeterinarianResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testReadByLicense() {
        webTestClient.get()
                .uri(VeterinarianResource.VETERINARIANS + VeterinarianResource.LICENSE, ClinicSeeder.LICENSE_DR_SMITH)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Veterinarian.class)
                .value(vet -> assertThat(vet.getLicenseNumber()).isEqualTo(ClinicSeeder.LICENSE_DR_SMITH));
    }
}
