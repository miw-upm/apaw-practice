package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TablePersistenceMongodbIT {

    @Autowired
    private TablePersistenceMongodb tablePersistence;

    @Test
    void testNoExistNumber(){
        assertFalse(this.tablePersistence.existNumber(10));
    }

    @Test
    void testReadAndUpdate(){
        Table table = this.tablePersistence.readByNumber(4).toTable();
        table.setPrice(new BigDecimal(30.99));
        this.tablePersistence.update(table);
        assertEquals(table.getPrice(), new BigDecimal(30.99));
    }

}
