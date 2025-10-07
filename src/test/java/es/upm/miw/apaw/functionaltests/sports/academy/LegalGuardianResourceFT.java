package es.upm.miw.apaw.functionaltests.sports.academy;

import es.upm.miw.apaw.adapters.resources.sports.academy.LegalGuardianResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class LegalGuardianResourceFT extends BaseSportsAcademyFT {
    
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate(){
        var id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
        var legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(id).build())
                .secondMobile("+5549988706208")
                .relationShip(RelationShip.OTHER)
                .build();
        webTestClient.put()
                .uri(LegalGuardianResource.LEGAL_GUARDIANS + LegalGuardianResource.ID_ID, id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(legalGuardian)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalGuardian.class)
                .value(legalGuardianResponse -> {
                    assertThat(legalGuardianResponse).isNotNull();
                    assertThat(legalGuardianResponse.getUser().getId()).isEqualTo(id);
                    assertThat(legalGuardianResponse.getRelationShip()).isEqualTo(RelationShip.OTHER);
                    assertThat(legalGuardianResponse.getSecondMobile()).isEqualTo("+5549988706208");
                });
    }
}
