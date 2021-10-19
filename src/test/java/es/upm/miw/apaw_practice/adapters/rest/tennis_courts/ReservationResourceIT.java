package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class ReservationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    /*@Test
    void testDelete(){
        this.webTestClient.delete()
                .uri(ReservationResource.RESERVATIONS + ReservationResource.OWNER_NAME);

    }*/
}
