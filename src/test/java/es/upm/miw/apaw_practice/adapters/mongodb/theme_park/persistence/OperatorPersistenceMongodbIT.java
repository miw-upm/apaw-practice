package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.theme_park.Operator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class OperatorPersistenceMongodbIT {

    @Autowired
    private OperatorPersistenceMongodb operatorPersistence;

    @Test
    void testReadByIdEmployee() {
        Operator operator = this.operatorPersistence.readByIdEmployee("65123A");
        assertEquals("Jude", operator.getNick());
        assertEquals(LocalDateTime.of(2018,3,10,13,1), operator.getRegistrationDate());
        assertEquals("Dragon Khan", operator.getRide().getName());
    }

}
