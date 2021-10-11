package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.SongRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.SongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("songPersistence")
public class SongPersistenceMongodb implements SongPersistence {
    private final SongRepository songRepository;

    @Autowired
    public SongPersistenceMongodb(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void delete(String id) {
         this.songRepository.deleteById(id);
    }
}
