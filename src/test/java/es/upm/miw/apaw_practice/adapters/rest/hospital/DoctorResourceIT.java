package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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
                .consumeWith(entityList -> {
                    assertNotNull(entityList.getResponseBody());
                    List<String> nickList = entityList.getResponseBody();
                    assertTrue(nickList.containsAll(Arrays.asList("John","Marta","Jose")));
                });
    }
}
