package es.upm.miw.apaw_practice.adapters.mongodb.music_festival;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.ConcertArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.MusicFestivalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.StageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.MusicFestivalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                new StageEntity("MainStage", "Central Park", 10000, LocalDateTime.of(2025, 5, 15, 14, 0)),
                new StageEntity("SecondStage", "Open Air Theater", 5000, LocalDateTime.of(2025, 6, 15, 15, 0)),
                new StageEntity("DanceFloor", "North Stadium", 8000, LocalDateTime.of(2025, 9, 10, 16, 30)),
                new StageEntity("AcousticCorner", "Small Hall", 2000, LocalDateTime.of(2025, 12, 10, 18, 0)),
                new StageEntity("SummerStage", "Seaside Park", 6000, LocalDateTime.of(2025, 6, 16, 14, 0)),
                new StageEntity("RockStage", "City Arena", 9000, LocalDateTime.of(2025, 9, 11, 15, 0)),
                new StageEntity("FusionStage", "Central Grounds", 7000, LocalDateTime.of(2025, 8, 12, 14, 0)),
                new StageEntity("IndieStage", "Green Park", 4000, LocalDateTime.of(2025, 8, 13, 16, 0)),
                new StageEntity("EDMStage", "Night Plaza", 8000, LocalDateTime.of(2025, 8, 14, 18, 0)),
                new StageEntity("ArenaStage", "Main Arena", 12000, LocalDateTime.of(2025, 7, 22, 14, 0)),
                new StageEntity("SideStage", "West End", 5000, LocalDateTime.of(2025, 7, 23, 15, 0)),
                new StageEntity("TestStage1", "TestStage1", 6000, LocalDateTime.of(2025, 7, 24, 16, 0)),
                new StageEntity("TestStage2", "TestStage2", 1000, LocalDateTime.of(2025, 6, 9, 18, 30))
        );
        return this.stageRepository.saveAll(stages);
    }

    private List<ConcertArtistEntity> seedConcertArtists() {
        List<ConcertArtistEntity> artists = Arrays.asList(
                new ConcertArtistEntity("The Fabulous", "Mexican", 4.5),
                new ConcertArtistEntity("DJ Moon", "Spanish", 4.2),
                new ConcertArtistEntity("ElectroBand", "Argentinian", 4.7),
                new ConcertArtistEntity("FolkSingers", "Colombian", 4.1),
                new ConcertArtistEntity("RockPower", "Chilean", 4.6),
                new ConcertArtistEntity("PopStar", "American", 4.0),
                new ConcertArtistEntity("Flamenco Duo", "Spanish", 4.9),
                new ConcertArtistEntity("IndieWave", "English", 4.3)
        );
        return this.concertArtistRepository.saveAll(artists);
    }

    private List<ConcertEntity> seedConcerts(List<StageEntity> stages, List<ConcertArtistEntity> artists) {
        return Arrays.asList(
                new ConcertEntity("CON001", LocalDate.of(2025, 5, 15), BigDecimal.valueOf(60.50), false, stages.get(0), Arrays.asList(artists.get(0), artists.get(1), artists.get(2))),
                new ConcertEntity("CON002", LocalDate.of(2025, 6, 15), BigDecimal.valueOf(55.00), false, stages.get(0), Arrays.asList(artists.get(0), artists.get(3), artists.get(7))),
                new ConcertEntity("CON005", LocalDate.of(2025, 6, 16), BigDecimal.valueOf(58.50), false, stages.get(4), Arrays.asList(artists.get(1), artists.get(5), artists.get(6))),
                new ConcertEntity("CON003", LocalDate.of(2025, 9, 10), BigDecimal.valueOf(65.00), false, stages.get(2), Arrays.asList(artists.get(2), artists.get(4), artists.get(5))),
                new ConcertEntity("CON006", LocalDate.of(2025, 9, 11), BigDecimal.valueOf(66.00), true, stages.get(2), Arrays.asList(artists.get(0), artists.get(2), artists.get(7))),
                new ConcertEntity("CON007", LocalDate.of(2025, 8, 12), BigDecimal.valueOf(62.00), false, stages.get(6), Arrays.asList(artists.get(3), artists.get(5), artists.get(6))),
                new ConcertEntity("CON008", LocalDate.of(2025, 8, 13), BigDecimal.valueOf(64.50), false, stages.get(7), Arrays.asList(artists.get(2), artists.get(4), artists.get(7))),
                new ConcertEntity("CON009", LocalDate.of(2025, 8, 14), BigDecimal.valueOf(59.00), true, stages.get(8), Arrays.asList(artists.get(0), artists.get(1), artists.get(7))),
                new ConcertEntity("CON010", LocalDate.of(2025, 7, 22), BigDecimal.valueOf(72.00), false, stages.get(9), Arrays.asList(artists.get(0), artists.get(4), artists.get(7))),
                new ConcertEntity("CON011", LocalDate.of(2025, 7, 23), BigDecimal.valueOf(68.50), false, stages.get(10), Arrays.asList(artists.get(1), artists.get(2), artists.get(5)))
        );
    }

    private void seedMusicFestivals(List<ConcertEntity> concerts) {
        List<MusicFestivalEntity> festivals = Arrays.asList(
                new MusicFestivalEntity("SpringFest", LocalDateTime.of(2025, 5, 1, 10, 0), BigDecimal.valueOf(200000), Arrays.asList(concerts.get(0))),
                new MusicFestivalEntity("SummerBeat", LocalDateTime.of(2025, 6, 1, 9, 0), BigDecimal.valueOf(180000), Arrays.asList(concerts.get(1), concerts.get(2))),
                new MusicFestivalEntity("AutumnRock", LocalDateTime.of(2025, 9, 1, 11, 0), BigDecimal.valueOf(150000), Arrays.asList(concerts.get(3), concerts.get(4))),
                new MusicFestivalEntity("MultiGenreFest", LocalDateTime.of(2025, 8, 12, 14, 0), BigDecimal.valueOf(300000), Arrays.asList(concerts.get(5), concerts.get(6), concerts.get(7))),
                new MusicFestivalEntity("MegaFestival", LocalDateTime.of(2025, 7, 22, 16, 0), BigDecimal.valueOf(500000), Arrays.asList(concerts.get(8), concerts.get(9)))
        );
        this.musicFestivalRepository.saveAll(festivals);
    }
}
