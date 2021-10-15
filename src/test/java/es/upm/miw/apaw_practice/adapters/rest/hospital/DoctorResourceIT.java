package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class DoctorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadDoctorNicks(){
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(DoctorResource.DOCTORS + DoctorResource.NICK)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(nicks -> assertTrue(nicks.size() > 0))
                .value(nicks -> assertEquals("John",nicks.get(0)));
    }
}
