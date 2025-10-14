package es.upm.miw.apaw.functionaltests.university;

import es.upm.miw.apaw.adapters.mongodb.university.daos.EnrollmentRepository;
import es.upm.miw.apaw.adapters.resources.university.EnrollmentResource;
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
public class EnrollmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void testDelete() {
        String enrollmentCode = "ENR004";
        
        assertThat(enrollmentRepository.findByCode(enrollmentCode)).isPresent();
        
        webTestClient.delete()
                .uri(EnrollmentResource.ENROLLMENTS + EnrollmentResource.CODE_ID, enrollmentCode)
                .exchange()
                .expectStatus().isOk();
        
        assertThat(enrollmentRepository.findByCode(enrollmentCode)).isEmpty();
    }

    @Test
    void testDeleteNotFound() {
        String nonExistentCode = "NONEXISTENT";
        
        assertThat(enrollmentRepository.findByCode(nonExistentCode)).isEmpty();
        
        webTestClient.delete()
                .uri(EnrollmentResource.ENROLLMENTS + EnrollmentResource.CODE_ID, nonExistentCode)
                .exchange()
                .expectStatus().isOk();
        
        assertThat(enrollmentRepository.findByCode(nonExistentCode)).isEmpty();
    }
}
