package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
class WaiterPersistenceMongodbIT {

    @Autowired
    private WaiterPersistenceMongodb waiterPersistence;

    @Test
    void testCreateAndFind(){
        Waiter waiter = new Waiter("bar","employee");
        this.waiterPersistence.create(waiter);
        assertEquals("bar", this.waiterPersistence.findBySectionAndCategory("bar","employee")
                                        .collect(Collectors.toList()).get(0).getSection());
    }
}
