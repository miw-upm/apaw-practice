package es.upm.miw.apaw.functionaltests.studentcouncil;

import es.upm.miw.apaw.adapters.resources.studentcouncil.StudentCouncilResource;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.services.studentcouncil.StudentCouncilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StudentCouncilResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private StudentCouncilService studentCouncilService;

    @Test
    void testPatchUpdateResources() {
        UUID id = UUID.randomUUID();
        BigDecimal newResources = BigDecimal.valueOf(12000.50);

        StudentCouncil council = StudentCouncil.builder()
                .id(id)
                .council("ETSII")
                .site("Madrid")
                .resources(newResources)
                .build();

        given(studentCouncilService.updateResources(id, newResources)).willReturn(council);

        webTestClient.patch()
                .uri(StudentCouncilResource.STUDENT_COUNCILS + "/" + id)
                .bodyValue(newResources)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentCouncil.class)
                .value(sc -> assertThat(sc.getResources()).isEqualByComparingTo(newResources));
    }
}