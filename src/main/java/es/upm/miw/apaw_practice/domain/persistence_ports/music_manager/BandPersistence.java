package es.upm.miw.apaw_practice.domain.persistence_ports.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import org.springframework.stereotype.Repository;

@Repository
public interface BandPersistence {
    Band create(Band band);
}