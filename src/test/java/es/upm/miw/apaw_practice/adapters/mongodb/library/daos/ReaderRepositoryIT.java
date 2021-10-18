package es.upm.miw.apaw_practice.adapters.mongodb.library.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.ReaderEntity;
import es.upm.miw.apaw_practice.domain.models.library.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ReaderRepositoryIT {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void testFindByEmail() {
        ReaderEntity readerEntity = this.readerRepository.findByEmail("jo@xpto.com").get();
        assertEquals("Joana", readerEntity.getNick());
        assertEquals(Gender.F, readerEntity.getGender());
    }

    @Test
    void testDeleteByEmail() {
        this.readerRepository.deleteByEmail("al@xpto.com");
        assertFalse(this.readerRepository.findByEmail("al@xpto.com").isPresent());
    }
}
