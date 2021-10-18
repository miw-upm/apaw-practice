package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class ReservationPersistenceMongodbIT {

    @Autowired
    private ReservationPersistenceMongoDB reservationPersistenceMongoDB;

    @Test
    void testDelete(){

    }

}
