package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import org.springframework.stereotype.Repository;

@Repository
public interface StagePersistence {

    Stage create(Stage stage);

    void delete(String name);

    boolean existName(String name);

    Stage readByName(String name);

}
