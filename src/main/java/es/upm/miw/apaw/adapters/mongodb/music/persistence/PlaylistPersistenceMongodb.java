package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.PlaylistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.persistenceports.music.PlaylistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("playlistPersistenceMongodb")
public class PlaylistPersistenceMongodb implements PlaylistPersistence {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistPersistenceMongodb(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void delete(String code) {
        if (!this.playlistRepository.existsById(code)) {
            throw new NotFoundException("Playlist not found: " + code);
        }
        this.playlistRepository.deleteById(code);
    }

    @Override
    public void update(String code, Playlist playlist) {
        PlaylistEntity entity = this.playlistRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Playlist not found: " + code));

        if (playlist.getLabel() != null) {
            entity.setLabel(playlist.getLabel());
        }
        if (playlist.getOpened() != null) {
            entity.setOpened(playlist.getOpened());
        }
        this.playlistRepository.save(entity); // ✅ sin return
    }
}
