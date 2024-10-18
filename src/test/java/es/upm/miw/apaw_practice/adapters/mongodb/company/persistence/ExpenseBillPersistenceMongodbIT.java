package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ExpenseBillRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.ExpenseBillPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ExpenseBillPersistenceMongodbIT {

    @Autowired
    private ExpenseBillPersistence expenseBillPersistence;

    @Autowired
    private ExpenseBillRepository expenseBillRepository;

    @Test
    void testDelete() {
        ExpenseBillEntity expenseBillEntity = new ExpenseBillEntity(
                "Test Description",
                new BigDecimal("150.0"),
                LocalDateTime.now(),
                null
        );
        ExpenseBillEntity savedEntity = this.expenseBillRepository.save(expenseBillEntity);
        assertTrue(this.expenseBillRepository.findById(savedEntity.getId()).isPresent());
        this.expenseBillPersistence.delete(savedEntity.getId());
        assertFalse(this.expenseBillRepository.findById(savedEntity.getId()).isPresent());
    }

    @Test
    void testDeleteNotFound() {

        assertThrows(NotFoundException.class, () -> this.expenseBillPersistence.delete("nonExistingId"));
    }
}
