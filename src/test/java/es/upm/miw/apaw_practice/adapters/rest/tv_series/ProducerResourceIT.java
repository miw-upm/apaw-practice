package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class ProducerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Producer producer = new Producer("New Line Cinema Ltd.","new@line.cinema",333222111L);
        this.webTestClient
                .put()
                .uri(ProducerResource.PRODUCERS + ProducerResource.BUSINESS_NAME,"New Line Cinema Inc.")
                .body(BodyInserters.fromValue(producer))
                .exchange()
                .expectStatus().isOk();

        producer = new Producer("New Line Cinema Inc.","newline@cinema.com", 987654321L);
        this.webTestClient
                .put()
                .uri(ProducerResource.PRODUCERS + ProducerResource.BUSINESS_NAME,"New Line Cinema Ltd.")
                .body(BodyInserters.fromValue(producer))
                .exchange()
                .expectStatus().isOk();
    }
}