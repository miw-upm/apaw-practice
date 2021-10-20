package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.library.Gender;
import es.upm.miw.apaw_practice.domain.models.library.Reader;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.ReaderPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ReaderServiceIT {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderPersistence readerPersistence;

    @Test
    void testCreate(){
        Reader reader = new Reader("José Rodrigues dos Santos", Gender.M,"rtp@xpto.com");
        Reader newReader = readerService.create(reader);
        assertEquals(newReader.getNick(),reader.getNick());
    }

    @Test
    void testAssertEmailNotExist(){
        assertThrows(ConflictException.class,()->this.readerService.assertEmailNotExist("ma@xpto.com"));
    }

    @Test
    void  testDelete(){
        readerService.delete("ma@xpto.com");
        assertFalse(readerPersistence.existEmail("ma@xpto.com"));
    }
}
