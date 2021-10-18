package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CustomerPersistenceMongodbIT {

    @Autowired
    private CustomerPersistenceMongodb customerPersistenceMongodb;

    @Autowired
    private EmarketerSeederService emarketerSeederService;

    @Test
    void testReadAll() {
        assertEquals(4, customerPersistenceMongodb.readAll().count());
    }

    @Test
    void testUpdate() {
        Customer customer = this.customerPersistenceMongodb.readByName("Pedro");
        assertEquals("particular", customer.getType());
        customer.setType("empresa");
        this.customerPersistenceMongodb.update(customer.getName(), customer);
        Customer customerUpdated = this.customerPersistenceMongodb.readByName("Pedro");
        assertEquals("empresa", customerUpdated.getType());
        emarketerSeederService.deleteAll();
        emarketerSeederService.seedDatabase();
    }

}
