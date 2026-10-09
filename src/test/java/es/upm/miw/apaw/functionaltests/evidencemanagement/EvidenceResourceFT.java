package es.upm.miw.apaw.functionaltests.evidencemanagement;

import es.upm.miw.apaw.adapters.in.evidencemanagement.EvidenceResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Collection;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EvidenceResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testFind() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Collection<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).mobile("600000000")
                    .firstName("Ana").familyName("Lopez").email("ana@example.com").build()).toList();
        });

        this.restTestClient.get()
                .uri(EvidenceResource.EVIDENCES
                        + "?confidential=false&longCustody=false&action=collected&custodianFirstName=ana")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Evidence[].class)
                .value(body -> {
                    assertThat(body).extracting(Evidence::getId)
                            .contains(EVIDENCE_ID_0).doesNotContain(EVIDENCE_ID_1, EVIDENCE_ID_2);
                    assertThat(body).filteredOn(evidence -> evidence.getId().equals(EVIDENCE_ID_0))
                            .singleElement().satisfies(evidence -> assertThat(evidence.getCustodyRecords())
                                    .allSatisfy(custodyRecord -> assertThat(custodyRecord.getCustodian().getFirstName())
                                            .isEqualTo("Ana")));
                });
    }
}
