package es.upm.miw.apaw.functionaltests.sports.academy;

import es.upm.miw.apaw.adapters.resources.sports.academy.ProfessorResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ProfessorResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate(){
        UUID id = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName("Mario Rossi")
                .mobile("+34711036811")
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        var professor = Professor.builder()
                .user(UserDto.builder().id(id).build())
                .specialization("Fitness")
                .licenseNumber("LIC123456")
                .build();
        webTestClient.post()
                .uri(ProfessorResource.PROFESSORS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(professor)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Professor.class)
                .value(professorResponse -> {
                    assertThat(professorResponse).isNotNull();
                    assertThat(professorResponse.getUser().getId()).isEqualTo(id);
                    assertThat(professorResponse.getSpecialization()).isEqualTo("Fitness");
                    assertThat(professorResponse.getLicenseNumber()).isEqualTo("LIC123456");
                });
    }
}
