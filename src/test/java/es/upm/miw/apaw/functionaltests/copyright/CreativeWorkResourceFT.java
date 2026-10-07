package es.upm.miw.apaw.functionaltests.copyright;

import es.upm.miw.apaw.adapters.in.copyright.CreativeWorkResource;
import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_0;
import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_1;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import es.upm.miw.apaw.domain.model.UserSnapshot;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreativeWorkResourceFT {

    @LocalServerPort
    private int port;

    @MockitoBean
    private UserFinder userFinder;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testFindWithAllCriteria() {
        java.util.List<UserSnapshot> mockUsers = java.util.List.of(
                UserSnapshot.builder()
                        .id(java.util.UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .firstName("cliente0")
                        .build()
        );
        org.mockito.BDDMockito.given(this.userFinder.findByIds(org.mockito.ArgumentMatchers.any()))
                .willReturn(mockUsers);

        this.restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(CreativeWorkResource.CREATIVE_WORKS + "/search")
                        .queryParam("authorPenName", WORK_0.getAuthorPenName())
                        .queryParam("claimUrgent", false)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreativeWork[].class)
                .value(works -> {
                    assertThat(works).hasSize(1);
                    assertThat(works[0].getRegistrationCode()).isEqualTo(WORK_0.getRegistrationCode());
                });
    }

    @Test
    void testGenerateClaimSummaries() {
        java.util.List<UserSnapshot> mockUsers = java.util.List.of(
                UserSnapshot.builder()
                        .id(java.util.UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .firstName("cliente0")
                        .build(),
                UserSnapshot.builder()
                        .id(java.util.UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .firstName("cliente1")
                        .build()
        );
        org.mockito.BDDMockito.given(this.userFinder.findByIds(org.mockito.ArgumentMatchers.any()))
                .willReturn(mockUsers);

        this.restTestClient.get()
                .uri(CreativeWorkResource.CREATIVE_WORKS + "/claim-summaries")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreativeWorkClaimSummary[].class)
                .value(summaries -> {
                    assertThat(summaries).isNotEmpty();
                    // El primero (RW-002) tiene 12500
                    assertThat(summaries[0].getRegistrationCode()).isEqualTo(WORK_1.getRegistrationCode());
                    assertThat(summaries[0].getClaimCount()).isEqualTo(1L);
                    assertThat(summaries[0].getTotalRequestedCompensation())
                            .isEqualByComparingTo(new BigDecimal("12500.00"));
                    
                    // El segundo (RW-001) tiene 6000
                    assertThat(summaries[1].getRegistrationCode()).isEqualTo(WORK_0.getRegistrationCode());
                    assertThat(summaries[1].getClaimCount()).isEqualTo(2L);
                    assertThat(summaries[1].getTotalRequestedCompensation())
                            .isEqualByComparingTo(new BigDecimal("6000.00"));
                });
    }

    @Test
    void testCreate() {
        java.util.UUID authorId = java.util.UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
        UserSnapshot mockUser = UserSnapshot.builder().id(authorId).firstName("cliente0").build();
        org.mockito.BDDMockito.given(this.userFinder.read(org.mockito.ArgumentMatchers.any())).willReturn(mockUser);

        es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation creation = 
                es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation.builder()
                .registrationCode("RW-NEW-FT")
                .title("Functional Test Work")
                .estimatedValuation(new BigDecimal("100.0"))
                .authorPenName("New Pen Name")
                .authorId(authorId)
                .build();

        this.restTestClient.post()
                .uri(CreativeWorkResource.CREATIVE_WORKS)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreativeWork.class)
                .value(work -> {
                    assertThat(work).isNotNull();
                    assertThat(work.getRegistrationCode()).isEqualTo("RW-NEW-FT");
                    assertThat(work.getTitle()).isEqualTo("Functional Test Work");
                    assertThat(work.getAuthor()).isNotNull();
                });
    }
}
