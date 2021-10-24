package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("platformPersistence")
public class PlatformPersistenceMongodb implements PlatformPersistence {

    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformPersistenceMongodb(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public Stream<Platform> readAll() {
        return this.platformRepository
                .findAll().stream()
                .map(PlatformEntity::toPlatform);
    }

    @Override
    public Platform create(Platform platform) {
        return this.platformRepository
                .save(new PlatformEntity(platform))
                .toPlatform();
    }

    @Override
    public Platform update(String consoleName, Platform platform) {
        PlatformEntity platformEntity = this.platformRepository
                .findByConsoleName(platform.getConsoleName())
                .orElseThrow(() -> new NotFoundException("Platform console name: " + platform.getConsoleName()));
        platformEntity.fromPlatform(platform);
        return this.platformRepository
                .save(platformEntity)
                .toPlatform();
    }

    @Override
    public Platform read(String consoleName) {
        return this.platformRepository
                .findByConsoleName(consoleName)
                .orElseThrow(() -> new NotFoundException("Platform console name: " + consoleName))
                .toPlatform();
    }

    @Override
    public boolean existConsoleName(String consoleName) {
        return this.platformRepository
                .findByConsoleName(consoleName)
                .isPresent();
    }
}
