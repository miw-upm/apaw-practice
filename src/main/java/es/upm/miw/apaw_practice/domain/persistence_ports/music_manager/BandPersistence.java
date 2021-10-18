package es.upm.miw.apaw_practice.domain.persistence_ports.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BandPersistence {
    Band create(Band band);

    Band read(String bandName);

    Band update(String bandName, Band band);

    Stream<Band> readAll();
}