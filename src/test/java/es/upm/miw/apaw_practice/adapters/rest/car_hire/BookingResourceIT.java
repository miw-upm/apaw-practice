package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.CarHireSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class BookingResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CarHireSeederService carHireSeederService;

    @AfterEach
    void seedDatabase() {
        this.carHireSeederService.deleteAll();
        this.carHireSeederService.seedDatabase();
    }

    @Test
    void testDeleteByBookingNumber() {
        String bookingNumber = "1403";

        assertTrue(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        bookingEntity.getBookingNumber().equals(bookingNumber)));

        this.webTestClient
                .delete()
                .uri(BookingResource.BOOKING + BookingResource.BOOKING_NUMBER, bookingNumber)
                .exchange()
                .expectStatus().isOk();

        assertFalse(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        bookingEntity.getBookingNumber().equals(bookingNumber)));
    }

    @Test
    void testGetVehiclesVinNumberByRentersName() {
        List<String> vinNumbers = this.vehicleRepository.findAll().stream()
                .map(VehicleEntity::toVehicle)
                .map(Vehicle::getVinNumber)
                .collect(Collectors.toList());

        this.webTestClient
                .get()
                .uri(BookingResource.BOOKING + BookingResource.RENTERS + BookingResource.RENTERS_NAME, "Pablo")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Vehicle.class)
                .value(vehicleRequestedList -> assertEquals(vinNumbers.get(0), vehicleRequestedList.get(0).getVinNumber()));

        this.webTestClient
                .get()
                .uri(BookingResource.BOOKING + BookingResource.RENTERS + BookingResource.RENTERS_NAME, "Manuel")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Vehicle.class)
                .value(vehicleRequestedList -> assertEquals(vinNumbers.get(2), vehicleRequestedList.get(0).getVinNumber()))
                .value(vehicleRequestedList -> assertEquals(vinNumbers.get(3), vehicleRequestedList.get(1).getVinNumber()));
    }

    @Test
    void testGetRentersNameByModelType() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BookingResource.BOOKING + BookingResource.VEHICLES + BookingResource.SEARCH)
                        .queryParam("q", "Model_Type:Opel Insignia")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Renter.class)
                .value(renters -> assertEquals(2, renters.size()))
                .value(renters -> assertEquals("Pablo", renters.get(0).getName()))
                .value(renters -> assertEquals("Alejandro", renters.get(1).getName()));

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BookingResource.BOOKING + BookingResource.VEHICLES + BookingResource.SEARCH)
                        .queryParam("q", "Model_Type:Seat Ibiza")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Renter.class)
                .value(renters -> assertEquals(1, renters.size()))
                .value(renters -> assertEquals("Manuel", renters.get(0).getName()));
    }
}
