package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.library.Reader;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.ReaderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    private final ReaderPersistence readerPersistence;

    @Autowired
    public ReaderService(ReaderPersistence readerPersistence) {
        this.readerPersistence = readerPersistence;
    }

    public Reader create(Reader reader) {
        this.assertEmailNotExist(reader.getEmail());
        return this.readerPersistence.create(reader);
    }

    public void assertEmailNotExist(String email) {
        if (this.readerPersistence.existEmail(email)) {
            throw new ConflictException("Email exist: " + email);
        }
    }

    public void delete(String email) {
        this.readerPersistence.delete(email);
    }
}
