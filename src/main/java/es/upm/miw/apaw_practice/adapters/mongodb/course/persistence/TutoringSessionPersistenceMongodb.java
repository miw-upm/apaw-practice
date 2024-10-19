package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.TutoringSessionPersistence;
import org.springframework.stereotype.Repository;

@Repository("tutoringSessionPersistence")
public class TutoringSessionPersistenceMongodb implements TutoringSessionPersistence {

    private final TutoringSessionRepository tutoringSessionRepository;

    public TutoringSessionPersistenceMongodb(TutoringSessionRepository tutoringSessionRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
    }

    @Override
    public void delete(String title) {

        if (this.tutoringSessionRepository.existsByTitle(title)){
            this.tutoringSessionRepository.deleteByTitle(title);
        } else {
            throw new NotFoundException("Tuttoring Session: " + title);
        }
    }
}
