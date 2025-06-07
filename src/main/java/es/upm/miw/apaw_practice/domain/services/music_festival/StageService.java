package es.upm.miw.apaw_practice.domain.services.music_festival;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Concert;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.StagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageService {
    private final StagePersistence stagePersistence;
    private final MusicFestivalPersistence musicFestivalPersistence;

    @Autowired
    public StageService(StagePersistence stagePersistence, MusicFestivalPersistence concertPersistence) {
        this.stagePersistence = stagePersistence;
        this.musicFestivalPersistence = concertPersistence;
    }

    public Stage create(Stage stage) {
        this.assertStageNameNotExist(stage.getName());
        return this.stagePersistence.create(stage);
    }

    public void delete(String name) {
        this.stagePersistence.delete(name);
    }

    public Stage findCapacitySumByConcertArtist(String concertArtistName) {
        int totalCapacity = this.musicFestivalPersistence.readAll()
                .flatMap(festival -> festival.getConcerts().stream())
                .filter(concert -> concert.getArtists().stream()
                        .anyMatch(artist -> concertArtistName.equals(artist.getName())))
                .map(Concert::getStage)
                .mapToInt(Stage::getCapacity)
                .sum();

        Stage stage = new Stage();
        stage.setCapacity(totalCapacity);
        return stage;
    }

    public Stage read(String name) {
        return this.stagePersistence.readByName(name);
    }

    private void assertStageNameNotExist(String name) {
        if (this.stagePersistence.existName(name)) {
            throw new ConflictException("Stage name already exists : " + name);
        }
    }

}