package es.upm.miw.apaw_practice.adapters.rest.coffee_shop;

import com.sun.jna.platform.win32.COM.IStream;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.coffee_shop.Dining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.BodyInserters;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class DiningResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testPostWire() {
        Dining diningCreated = new Dining("5", "location5", 6);
        this.webTestClient
                .post()
                .uri(DiningResource.DINING)
                .body(BodyInserters.fromValue(diningCreated))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Dining.class)
                .value(Assertions::assertNotNull)
                .value(dining -> {
                    assertEquals(diningCreated.getDiningNumber(), dining.getDiningNumber());
                    assertEquals(diningCreated.getLocation(), dining.getLocation());
                    assertEquals(diningCreated.getCapacity(), dining.getCapacity());
                });
    }

    @Test
    void testPostAlreadyExistDining() {
        Dining dining = new Dining("1", "location1", 6);
        this.webTestClient
                .post()
                .uri(DiningResource.DINING)
                .body(BodyInserters.fromValue(dining))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testGetLocationByClientName() {
        this.webTestClient
                .get()
                .uri(DiningResource.DINING + DiningResource.NAME, "client2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(location -> {
                    assertEquals(List.of("location2"), location);
                });
    }
}
