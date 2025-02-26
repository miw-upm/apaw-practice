package es.upm.miw.apawpractice.functionaltests.system;

import es.upm.miw.apawpractice.adapters.rest.system.SystemResource;
import es.upm.miw.apawpractice.functionaltests.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RestTestConfig
class SystemResourceFunctionalTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testReadBadge() {
        String url = "http://localhost:" + this.port + SystemResource.VERSION_BADGE;
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        String body = response.getBody();
        assertThat(body)
                .isNotNull()
                .startsWith("<svg");
    }

    @Test
    void testReadInfo() {
        String url = "http://localhost:" + this.port + SystemResource.SYSTEM + SystemResource.APP_INFO;
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        String body = response.getBody();
        assertThat(body)
                .isNotNull()
                .isNotEmpty();
    }
}
