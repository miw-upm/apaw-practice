package es.upm.miw.apaw_practice.domain.services.music_festival;

import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.StagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageService {
    private final StagePersistence stagePersistence;

    @Autowired
    public StageService(StagePersistence stagePersistence) {
        this.stagePersistence = stagePersistence;
    }

    public void delete(String name) {
        this.stagePersistence.delete(name);
    }
}