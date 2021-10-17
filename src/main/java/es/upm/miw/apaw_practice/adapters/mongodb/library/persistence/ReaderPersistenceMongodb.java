package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.ReaderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.ReaderEntity;
import es.upm.miw.apaw_practice.domain.models.library.Reader;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.ReaderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("readerPersistence")
public class ReaderPersistenceMongodb implements ReaderPersistence {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderPersistenceMongodb(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Reader create(Reader reader) {
        ReaderEntity readerEntity = new ReaderEntity(reader.getNick(),reader.getGender(),reader.getEmail());
        return this.readerRepository
                .save(readerEntity).toReader();
    }

    @Override
    public boolean existEmail(String email) {
        return this.readerRepository
                .findByEmail(email)
                .isPresent();
    }
}
