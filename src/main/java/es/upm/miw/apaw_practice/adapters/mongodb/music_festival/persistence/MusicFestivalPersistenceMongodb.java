package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.ConcertArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.MusicFestivalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.StageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.MusicFestivalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("musicFestivalPersistence")
public class MusicFestivalPersistenceMongodb implements MusicFestivalPersistence {
    private final MusicFestivalRepository musicFestivalRepository;
    private final StageRepository stageRepository;
    private final ConcertArtistRepository concertArtistRepository;

    @Autowired
    public MusicFestivalPersistenceMongodb(MusicFestivalRepository musicFestivalRepository,
                                           StageRepository stageRepository,
                                           ConcertArtistRepository concertArtistRepository) {
        this.musicFestivalRepository = musicFestivalRepository;
        this.stageRepository = stageRepository;
        this.concertArtistRepository = concertArtistRepository;
    }

    @Override
    public Stream<MusicFestival> readAll() {
        return this.musicFestivalRepository.findAll().stream()
                .map(MusicFestivalEntity::toMusicFestival);
    }

    @Override
    public MusicFestival readByName(String name) {
        return findMusicFestivalEntityByName(name)
                .toMusicFestival();
    }

    @Override
    public MusicFestival update(MusicFestival musicFestival) {
        MusicFestivalEntity musicFestivalEntity = findMusicFestivalEntityByName(musicFestival.getName());
        List<ConcertEntity> concertEntities = musicFestival.getConcerts().stream()
                .map(concert -> {
                    StageEntity stageEntity = this.stageRepository.findByName(concert.getStage().getName())
                            .orElseThrow(() -> new NotFoundException("Stage name:" + concert.getStage().getName()));
                    List<ConcertArtistEntity> artistEntities = concert.getArtists().stream()
                            .map(artist -> this.concertArtistRepository.findByName(artist.getName())
                                    .orElseThrow(() -> new NotFoundException("ConcertArtist name:" + artist.getName())))
                            .toList();
                    return new ConcertEntity(concert.getDate(),
                            concert.getTicketPrice(), concert.isSoldOut(), stageEntity, artistEntities);
                }).toList();
        musicFestivalEntity.setConcerts(concertEntities);
        return this.musicFestivalRepository.save(musicFestivalEntity).toMusicFestival();
    }

    @Override
    public MusicFestival updateBudget(String name, BigDecimal budget) {
        MusicFestivalEntity musicFestivalEntity = findMusicFestivalEntityByName(name);
        musicFestivalEntity.setBudget(budget);
        return this.musicFestivalRepository
                .save(musicFestivalEntity)
                .toMusicFestival();
    }

    private MusicFestivalEntity findMusicFestivalEntityByName(String name) {
        return this.musicFestivalRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("MusicFestival name:" + name));
    }
}
