package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningPersistence screeningPersistence;

    @Autowired
    public ScreeningService(ScreeningPersistence screeningPersistence) {
        this.screeningPersistence = screeningPersistence;
    }

    public List<Screening> findAll() {
        return screeningPersistence.findAll();
    }

    public Screening findByScreeningTime(String screeningTime) {
        return screeningPersistence.findByScreeningTime(screeningTime)
                .orElseThrow(() -> new RuntimeException("Screening not found: " + screeningTime));
    }

    public Screening create(Screening screening) {
        return screeningPersistence.save(screening);
    }
}