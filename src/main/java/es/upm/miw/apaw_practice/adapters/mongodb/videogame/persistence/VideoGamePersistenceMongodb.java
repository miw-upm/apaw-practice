package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("videoGamePersistence")
public class VideoGamePersistenceMongodb implements VideoGamePersistence {
    private final VideoGameRepository videoGameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public VideoGamePersistenceMongodb(VideoGameRepository videoGameRepository, PlayerRepository playerRepository) {
        this.videoGameRepository = videoGameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public VideoGame read(String videoGameAlias){
        return this.videoGameRepository
                .findByVideoGameAlias(videoGameAlias)
                .orElseThrow(() -> new NotFoundException("VideoGame videoGameAlias: " + videoGameAlias))
                .toVideoGame();
    }

    @Override
    public boolean existVideoGameAlias(String videoGameAlias) {
        return this.videoGameRepository
                .findByVideoGameAlias(videoGameAlias)
                .isPresent();
    }

    @Override
    public VideoGame create(VideoGame videoGame) {
        return this.videoGameRepository
                .save(new VideoGamerEntity(videoGame))
                .toVideoGame();
    }

    @Override
    public Stream<VideoGame> readAll() {
        return this.videoGameRepository.findAll().stream()
                .map(VideoGamerEntity::toVideoGame);
    }

    @Override
    public VideoGame update(String videoGameAlias, VideoGame videoGame) {
        VideoGamerEntity videoGamerEntity = videoGameRepository
                .findByVideoGameAlias(videoGameAlias)
                .orElseThrow(() -> new NotFoundException("VideoGame videoGameAlias: " + videoGameAlias));
        videoGamerEntity.fromVideoGame(videoGame);
        return videoGameRepository
                .save(videoGamerEntity)
                .toVideoGame();
    }

    @Override
    public Stream<String> findPlayerNameByVideoGameAlias(String videoGameAlias) {
        return this.playerRepository.findAll().stream()
                .filter(playerEntity -> playerEntity.getConsoleEntity() != null)
                .flatMap(playerEntity -> playerEntity.getConsoleEntity().getVideoGameEntities().stream()
                        .filter(videoGamerEntity -> videoGamerEntity.getVideoGameAlias().equals(videoGameAlias))
                        .map(VideoGamerEntity -> playerEntity.getPlayerName())
                        .distinct());
    }
}
