package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

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
                .value(hotel -> assertEquals(1, hotel.size()));
    }

    @Test
    void testReadHotelNotFound(){
        String dni = "74599234P";
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS + DirectorResource.DNI, dni)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindHotelGuestDistinctDni(){
        String dni = "77777777V";
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS + DirectorResource.DNI + DirectorResource.HOTELGUEST, dni)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(HotelGuest.class)
                .value(hotelguest -> assertEquals(2, hotelguest.size()))
                .value(hotelguest -> assertEquals(List.of("88888888K", "11111111S"),
                        hotelguest.stream().map(HotelGuest::getDni)
                                .collect(Collectors.toList())));
    }
}
