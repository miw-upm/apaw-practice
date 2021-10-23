package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.CarHireSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BookingServiceIT {

    @Autowired
    BookingService bookingService;

    @Autowired
    CarHireSeederService carHireSeederService;

    @BeforeEach
    void seedDatabase() {
        this.carHireSeederService.deleteAll();
        this.carHireSeederService.seedDatabase();
    }

    @Test
    void testGetVehiclesVinNumberByRentersName() {
        assertThrows(NotFoundException.class, () -> this.bookingService.getVehiclesVinNumberByRentersName("Invented"));

        List<Vehicle> vehicles = this.bookingService.getVehiclesVinNumberByRentersName("Pablo").collect(Collectors.toList());
        assertNotNull(vehicles.get(0).getId());
        assertEquals("VSSZZZ6KZ1R149943", vehicles.get(0).getVinNumber());
        assertEquals(0, vehicles.get(0).getDailyCost().compareTo(new BigDecimal("50")));
        assertEquals(25400, vehicles.get(0).getKilometersAmount());
        assertEquals(Boolean.TRUE, vehicles.get(0).getGoodCondition());
    }

    @Test
    void testGetRentersNameByModelType() {
        assertThrows(NotFoundException.class, () -> this.bookingService.getRentersNameByModelType("Invented"));

        List<Renter> renters = new ArrayList<>(this.bookingService.getRentersNameByModelType("Opel Insignia"));
        assertEquals(2, renters.size());
        assertEquals("Pablo", renters.get(0).getName());
        assertNull(renters.get(0).getDni());
        assertNull(renters.get(0).getLikedCar());
        assertEquals("Alejandro", renters.get(1).getName());
        assertNull(renters.get(1).getDni());
        assertNull(renters.get(1).getLikedCar());

        renters.clear();
        renters.addAll(this.bookingService.getRentersNameByModelType("Seat Ibiza"));
        assertEquals(1, renters.size());
        assertEquals("Manuel", renters.get(0).getName());
        assertNull(renters.get(0).getDni());
        assertNull(renters.get(0).getLikedCar());
    }
}
