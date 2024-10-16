package es.upm.miw.apaw_practice.domain.services.course;

import es.upm.miw.apaw_practice.domain.persistence_ports.course.TutoringSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutoringSessionService {

    private final TutoringSessionPersistence tutoringSessionPersistence;

    @Autowired
    public TutoringSessionService(TutoringSessionPersistence tutoringSessionPersistence) {
        this.tutoringSessionPersistence = tutoringSessionPersistence;
    }

    public void delete(String tittle){

    };
}
