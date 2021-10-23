package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ShoppingCartPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoGameService {

    private final VideoGamePersistance videoGamePersistance;

    private final VideoGameCompanyPersistance videoGameCompanyPersistance;

    @Autowired
    public VideoGameService(VideoGamePersistance videoGamePersistance, VideoGameCompanyPersistance videoGameCompanyPersistance) {
        this.videoGamePersistance = videoGamePersistance;
        this.videoGameCompanyPersistance = videoGameCompanyPersistance;
    }

    public VideoGame read(String title) {
        return this.videoGamePersistance.readByTitle(title);
    }

    public void delete(String title) {
        this.videoGamePersistance.delete(title);
    }

    public Stream<VideoGame> findByPlatformsInVideoGameCompany() {
        List<String> consoleNames = this.videoGameCompanyPersistance.readAll()
                .flatMap(videoGameCompany -> videoGameCompany.getPlatforms().stream())
                .map(Platform::getConsoleName)
                .distinct()
                .collect(Collectors.toList());
        return this.videoGamePersistance.readAll()
                .filter(videoGame -> videoGame.getPlatforms().stream()
                        .map(Platform::getConsoleName)
                        .anyMatch(consoleNames::contains));
    }
}
