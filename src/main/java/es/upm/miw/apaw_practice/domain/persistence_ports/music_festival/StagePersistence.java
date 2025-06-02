package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import org.springframework.stereotype.Repository;

@Repository
public interface StagePersistence {
    Stage readByName(String name);

    void delete(String name);
}
