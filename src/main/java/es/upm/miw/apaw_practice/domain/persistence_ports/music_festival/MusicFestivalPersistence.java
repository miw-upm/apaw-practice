package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicFestivalPersistence {
    MusicFestival readByName(String name);
}
