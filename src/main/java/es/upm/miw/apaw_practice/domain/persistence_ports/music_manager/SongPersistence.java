package es.upm.miw.apaw_practice.domain.persistence_ports.music_manager;

import org.springframework.stereotype.Repository;

@Repository
public interface SongPersistence {
    void delete(String id);
}
