package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import es.upm.miw.apaw_practice.TestConfig;
import java.math.BigDecimal;
import java.time.LocalDate;

@TestConfig
class ReservationRepositoryIT {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.reservationRepository.findAll().stream()
                .anyMatch(reservation ->
                        reservation.getDate() != null &&
                                reservation.getDate().equals(LocalDate.now().plusDays(1)) &&
                                reservation.getPrice().compareTo(new BigDecimal("50.00")) == 0 &&
                                reservation.getNumberOfPeople() == 2 &&
                                reservation.getClubEntity().getName().equals("Cuenca Club") &&
                                reservation.getCustomerEntities().get(0).getName().equals("Pepe") &&
                                reservation.getCustomerEntities().get(1).getName().equals("Juan")
                ));
    }
}



