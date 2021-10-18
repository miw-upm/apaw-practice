package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ReservationPersistenceMongodbIT {

    @Autowired
    private ReservationPersistenceMongoDB reservationPersistenceMongoDB;

    @Test
    void testDeleteAndRead(){
        LocalDate localDate = LocalDate.of(2021, 9, 30);
        this.reservationPersistenceMongoDB.delete("Paco", localDate.atTime( 11,0));
        assertThrows(NotFoundException.class, () -> this.reservationPersistenceMongoDB.read("Paco", localDate.atTime(11, 0)));
    }

}
