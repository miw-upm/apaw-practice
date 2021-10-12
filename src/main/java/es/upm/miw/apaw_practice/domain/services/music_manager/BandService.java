package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.BandPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandService {

    private final BandPersistence bandPersistence;

    @Autowired
    public BandService(BandPersistence bandPersistence) {
        this.bandPersistence = bandPersistence;
    }

    public Band create(Band band) {
        return this.bandPersistence.create(band);
    }
}
