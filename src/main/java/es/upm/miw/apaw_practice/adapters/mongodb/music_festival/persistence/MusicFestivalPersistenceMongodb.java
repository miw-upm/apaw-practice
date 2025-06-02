package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.MusicFestivalRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import org.springframework.stereotype.Repository;

@Repository("musicFestivalPersistence")
public class MusicFestivalPersistenceMongodb implements MusicFestivalPersistence {
    private final MusicFestivalRepository musicFestivalRepository;

    public MusicFestivalPersistenceMongodb(MusicFestivalRepository musicFestivalRepository) {
        this.musicFestivalRepository = musicFestivalRepository;
    }

    @Override
    public MusicFestival readByName(String name) {
        return this.musicFestivalRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("MusicFestival name:"+name))
                .toMusicFestival();
    }
}
