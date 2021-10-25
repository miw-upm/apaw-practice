package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.ProducerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ProducerRepositoryIT {

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void testFindByBusinessName() {
        assertTrue(this.producerRepository.findByBusinessName("Ufotable, Inc.").isPresent());
        ProducerEntity producer = this.producerRepository.findByBusinessName("Ufotable, Inc.").get();
        assertEquals("ufotable@anime.com",producer.getEmail());
        assertEquals(123456789L,producer.getPhone());
    }
}
