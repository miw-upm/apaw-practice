package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.course.TutoringSessionPersistence;
import org.springframework.stereotype.Repository;

@Repository("tutoringSessionPersistence")
public class TutoringSessionPersistenceMongodb implements TutoringSessionPersistence {


    @Override
    public boolean delete(String tittle) {
        return false;
    }
}
