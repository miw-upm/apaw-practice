package es.upm.miw.apaw.functionaltests.leases;

import es.upm.miw.apaw.adapters.in.leases.LeaseResource;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LeaseResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testFindAmendmentReport() {
        this.restTestClient.get().uri(LeaseResource.LEASES + LeaseResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LeaseAmendmentReport[].class)
                .value(body -> {
                    assertThat(body).extracting(LeaseAmendmentReport::getTotalAdditionalAmount)
                            .isSortedAccordingTo(Comparator.reverseOrder());
                    assertThat(body).extracting(LeaseAmendmentReport::getLeaseType)
                            .contains(LeaseType.RESIDENTIAL, LeaseType.COMMERCIAL);
                });
    }
}
