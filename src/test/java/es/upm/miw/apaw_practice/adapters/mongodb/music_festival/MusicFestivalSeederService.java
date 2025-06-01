package es.upm.miw.apaw_practice.adapters.mongodb.music_festival;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.ConcertArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.MusicFestivalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.StageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.MusicFestivalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MusicFestivalSeederService {

    @Autowired
    private MusicFestivalRepository musicFestivalRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private ConcertArtistRepository concertArtistRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Music Festival Initial Load -----------");

        List<StageEntity> savedStages = seedStages();
        List<ConcertArtistEntity> savedArtists = seedConcertArtists();
        List<ConcertEntity> savedConcerts = seedConcerts(savedStages, savedArtists);
        seedMusicFestivals(savedConcerts);
    }

    public void deleteAll() {
        this.musicFestivalRepository.deleteAll();
        this.stageRepository.deleteAll();
        this.concertArtistRepository.deleteAll();
    }

    private List<StageEntity> seedStages() {
        List<StageEntity> stages = Arrays.asList(
                new StageEntity("MainStage", "Parque Central", 10000, LocalDateTime.of(2025, 5, 10, 14, 0)),
                new StageEntity("SecondStage", "Teatro Abierto", 5000, LocalDateTime.of(2025, 5, 11, 15, 0)),
                new StageEntity("DanceFloor", "Estadio Norte", 8000, LocalDateTime.of(2025, 5, 12, 16, 30)),
                new StageEntity("AcousticCorner", "Sala Pequeña", 2000, LocalDateTime.of(2025, 5, 13, 18, 0))
        );
        return this.stageRepository.saveAll(stages);
    }

    private List<ConcertArtistEntity> seedConcertArtists() {
        List<ConcertArtistEntity> artists = Arrays.asList(
                new ConcertArtistEntity("Los Fabulosos", "Mexicana", 4.5),
                new ConcertArtistEntity("DJ Luna", "Española", 4.2),
                new ConcertArtistEntity("ElectroBand", "Argentina", 4.7),
                new ConcertArtistEntity("FolkSingers", "Colombiana", 4.1),
                new ConcertArtistEntity("RockPower", "Chilena", 4.6),
                new ConcertArtistEntity("PopStar", "USA", 4.0),
                new ConcertArtistEntity("ClassicalDuo", "Frances", 4.9),
                new ConcertArtistEntity("IndieWave", "Ingles", 4.3)
        );
        return this.concertArtistRepository.saveAll(artists);
    }

    private List<ConcertEntity> seedConcerts(List<StageEntity> stages, List<ConcertArtistEntity> artists) {
        return Arrays.asList(
                new ConcertEntity("CON001", LocalDate.of(2025, 5, 15), BigDecimal.valueOf(60.50), false, stages.get(0),
                        Arrays.asList(artists.get(0), artists.get(1), artists.get(2))),
                new ConcertEntity("CON002", LocalDate.of(2025, 5, 16), BigDecimal.valueOf(55.00), false, stages.get(1),
                        Arrays.asList(artists.get(0), artists.get(3), artists.get(7))),
                new ConcertEntity("CON003", LocalDate.of(2025, 5, 17), BigDecimal.valueOf(65.00), false, stages.get(2),
                        Arrays.asList(artists.get(4), artists.get(5), artists.get(6))),
                new ConcertEntity("CON004", LocalDate.of(2025, 5, 18), BigDecimal.valueOf(70.00), true, stages.get(3),
                        Arrays.asList(artists.get(2), artists.get(5), artists.get(7)))
        );
    }

    private void seedMusicFestivals(List<ConcertEntity> concerts) {
        List<MusicFestivalEntity> festivals = Arrays.asList(
                new MusicFestivalEntity("SpringFest", LocalDateTime.of(2025, 5, 1, 10, 0), BigDecimal.valueOf(200000),
                        Arrays.asList(concerts.get(0))),
                new MusicFestivalEntity("SummerBeat", LocalDateTime.of(2025, 6, 1, 9, 0), BigDecimal.valueOf(180000),
                        Arrays.asList(concerts.get(0), concerts.get(1))),
                new MusicFestivalEntity("AutumnRock", LocalDateTime.of(2025, 9, 1, 11, 0), BigDecimal.valueOf(150000),
                        Arrays.asList(concerts.get(1), concerts.get(2))),
                new MusicFestivalEntity("WinterAcoustic", LocalDateTime.of(2025, 12, 5, 12, 30), BigDecimal.valueOf(100000),
                        Arrays.asList(concerts.get(3))),
                new MusicFestivalEntity("MultiGenreFest", LocalDateTime.of(2025, 8, 12, 14, 0), BigDecimal.valueOf(300000),
                        Arrays.asList(concerts.get(0), concerts.get(2), concerts.get(3))),
                new MusicFestivalEntity("MegaFestival", LocalDateTime.of(2025, 7, 22, 16, 0), BigDecimal.valueOf(500000),
                        Arrays.asList(concerts.get(0), concerts.get(1), concerts.get(2), concerts.get(3)))
        );
        this.musicFestivalRepository.saveAll(festivals);
    }
}
