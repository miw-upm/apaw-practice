package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.SongRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.music.Song;
import es.upm.miw.apaw.domain.persistenceports.music.SongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("songPersistenceMongodb")
public class SongPersistenceMongodb implements SongPersistence {

    private final SongRepository songRepository;

    @Autowired
    public SongPersistenceMongodb(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void create(Song song) {
        if (this.songRepository.existsById(song.getIsrc())) {
            throw new ConflictException("Song already exists: " + song.getIsrc());
        }

        SongEntity entity = new SongEntity();
        entity.setIsrc(song.getIsrc());
        entity.setTitle(song.getTitle());
        entity.setDurationSeconds(song.getDurationSeconds());
        entity.setStyleGenre(
                song.getStyle() != null ? song.getStyle().getGenre() : null
        );

        this.songRepository.save(entity);
    }
}
