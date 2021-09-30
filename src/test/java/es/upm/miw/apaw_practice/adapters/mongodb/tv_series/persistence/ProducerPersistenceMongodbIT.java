package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ProducerPersistenceMongodbIT {
    @Autowired
    private ProducerPersistenceMongodb producerPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.producerPersistence.read("Warner Bros"));
    }

    @Test
    void testBusinessNameNotExist() {
        assertFalse(this.producerPersistence.existBusinessName("Warner Bros"));
    }

    @Test
    void testBusinessNameExist() {
        assertTrue(this.producerPersistence.existBusinessName("New Line Cinema Inc."));
    }

    @Test
    void testCreateAndRead() {
        Producer producer = new Producer("Amazon Studios","studios@amazon.com",783246375L);
        this.producerPersistence.create(producer);
        Producer producerBD = this.producerPersistence.read("Amazon Studios");
        assertEquals("studios@amazon.com",producerBD.getEmail());
        assertEquals(783246375L,producerBD.getPhone());
    }

    @Test
    void testCreateAndUpdate() {
        Producer producerCreation = new Producer("FX Productions","fxproductions@fxproductions.com",329847239487L);
        Producer producerBD = this.producerPersistence.create(producerCreation);
        producerBD.setEmail("fx@productions.com");
        this.producerPersistence.update("FX Productions",producerBD);
        producerBD = this.producerPersistence.read("FX Productions");
        assertEquals("fx@productions.com",producerBD.getEmail());
    }
}
