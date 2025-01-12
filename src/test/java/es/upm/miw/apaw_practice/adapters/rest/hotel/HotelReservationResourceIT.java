package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.models.hotel.PatchReservationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.time.LocalDate;
import static es.upm.miw.apaw_practice.adapters.rest.hotel.HotelReservationResource.NUMBERS;
import static es.upm.miw.apaw_practice.adapters.rest.hotel.HotelReservationResource.RESERVATIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class HotelReservationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testPatchReservationRoom() {
        String roomNumber = "666";
        LocalDate date = null;
        HotelClient client = null;
        PatchReservationRequest request = new PatchReservationRequest();
        request.setReservationDate(date);
        request.setRoomNumber(roomNumber);
        request.setClient(client);
        LocalDate nonModifiedDate = LocalDate.of(2020,1,1);
        HotelReservation updatedReservation= this.webTestClient
                .patch()
                .uri(RESERVATIONS + NUMBERS, "1")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelReservation.class)
                .returnResult()
                .getResponseBody();
        assertEquals("666", updatedReservation.getRoomNumber());
        assertEquals(nonModifiedDate, updatedReservation.getReservationDate());
        assertEquals("David", updatedReservation.getClient().getName());

    }
    @Test
    void testPatchReservationDate() {
        String roomNumber = null;
        LocalDate date = LocalDate.of(1999,1,1);
        HotelClient client = null;
        PatchReservationRequest request = new PatchReservationRequest();
        request.setReservationDate(date);
        request.setRoomNumber(roomNumber);
        request.setClient(client);
        HotelReservation updatedReservation= this.webTestClient
                .patch()
                .uri(RESERVATIONS + NUMBERS, "2")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelReservation.class)
                .returnResult()
                .getResponseBody();
        assertEquals("202", updatedReservation.getRoomNumber());
        assertEquals(date, updatedReservation.getReservationDate());
        assertEquals("Mengtxu", updatedReservation.getClient().getName());
    }

    @Test
    void testPatchReservationClient() {
        String roomNumber = null;
        LocalDate date = null;
        HotelClient client = new HotelClient("x6666666x", "Alex", "666666666", "test@gmail.com");
        PatchReservationRequest request = new PatchReservationRequest();
        request.setReservationDate(date);
        request.setRoomNumber(roomNumber);
        request.setClient(client);
        LocalDate nonModifiedDate = LocalDate.of(2020,10,12);
        HotelReservation updatedReservation= this.webTestClient
                .patch()
                .uri(RESERVATIONS + NUMBERS, "3")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelReservation.class)
                .returnResult()
                .getResponseBody();
        assertEquals("303", updatedReservation.getRoomNumber());
        assertEquals(nonModifiedDate, updatedReservation.getReservationDate());
        assertEquals("x6666666x", updatedReservation.getClient().getIdentityDocument());
        assertEquals("Alex", updatedReservation.getClient().getName());
        assertEquals("666666666", updatedReservation.getClient().getPhone());
        assertEquals("test@gmail.com", updatedReservation.getClient().getEmail());

    }

    @Test
    void testPatchReservationNotFound(){
        String roomNumber = "666";
        LocalDate date = LocalDate.of(1999,1,1);
        HotelClient client = new HotelClient("x6666666x", "Alex", "666666666", "");
        PatchReservationRequest request = new PatchReservationRequest();
        this.webTestClient
                .patch()
                .uri(RESERVATIONS + NUMBERS, "4")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isNotFound();
    }
}
