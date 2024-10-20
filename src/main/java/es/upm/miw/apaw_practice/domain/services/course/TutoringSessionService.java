package es.upm.miw.apaw_practice.domain.services.course;

import es.upm.miw.apaw_practice.domain.persistence_ports.course.TutoringSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
public class TutoringSessionService {

    private final TutoringSessionPersistence tutoringSessionPersistence;

    @Autowired
    public TutoringSessionService(TutoringSessionPersistence tutoringSessionPersistence) {
        this.tutoringSessionPersistence = tutoringSessionPersistence;
    }

    public void delete(String tittle){
        this.tutoringSessionPersistence.delete(tittle);
    }

    public BigDecimal priceSumOfRoleDuration(String role, LocalTime duration) {

        return this.tutoringSessionPersistence.priceSumOfRoleDuration(role, duration);
    }
}
