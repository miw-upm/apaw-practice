package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Gender;
import es.upm.miw.apaw_practice.domain.models.library.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ReaderPersistenceMongodbIT {

    @Autowired
    private ReaderPersistenceMongodb readerPersistenceMongodb;

    @Test
    void testCreate() {
        Reader reader = new Reader("Mafalda", Gender.F, "mafa@xpto.com");
        Reader newReader = readerPersistenceMongodb.create(reader);
        assertEquals(newReader.getNick(), reader.getNick());
        assertEquals(newReader.getGender(), reader.getGender());
        assertEquals(newReader.getEmail(), reader.getEmail());
    }

    @Test
    void testExistEmail() {
        Reader reader = new Reader("Mafalda", Gender.F, "mafa@xpto.com");
        Reader newReader = readerPersistenceMongodb.create(reader);
        assertTrue(readerPersistenceMongodb.existEmail(newReader.getEmail()));
        assertFalse(readerPersistenceMongodb.existEmail("expo2020@xpto.com"));
    }

    @Test
    void testDelete() {
        this.readerPersistenceMongodb.delete("al@xpto.com");
        assertFalse(readerPersistenceMongodb.existEmail("al@xpto.com"));
    }
}
