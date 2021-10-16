package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class DirectorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadEmails() {
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Director.class)
                .value(directors -> Assertions.assertNull(directors.get(0).getDni()))
                .value(directors -> Assertions.assertNull(directors.get(1).getTelephone()))
                .value(directors -> assertEquals("email@email.com", directors.get(1).getEmail()));
    }

    @Test
    void testReadHotels(){
        String dni = "22222222P";
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS + DirectorResource.DNI, dni)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Hotel.class)
                .value(System.out::println)
                .value(hotel -> assertEquals(1, hotel.size()))
                .value(hotel -> assertEquals("Av. Luto, 23981", hotel.get(0).getDirection()));

    }
}
