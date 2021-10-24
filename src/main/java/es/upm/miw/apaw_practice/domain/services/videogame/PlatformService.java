package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.PlatformMemoryUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PlatformService {

    private final PlatformPersistence platformPersistence;

    @Autowired
    public PlatformService(PlatformPersistence platformPersistence) {
        this.platformPersistence = platformPersistence;
    }

    public Platform create(Platform platform) {
        platform.setModel(platform.getModel());
        this.assertConsoleNameNotExist(platform.getConsoleName());
        return this.platformPersistence.create(platform);
    }

    public void assertConsoleNameNotExist(String consoleName) {
        if (this.platformPersistence.existConsoleName(consoleName)) {
            throw new ConflictException("ConsoleName exists: " + consoleName);
        }
    }

    public void updateMemory(Stream<PlatformMemoryUpdating> platformMemoryUpdatingList) {
        platformMemoryUpdatingList.map(platformNewMemory -> {
                    Platform platform = this.platformPersistence.read(platformNewMemory.getConsoleName());
                    platform.setMemory(platformNewMemory.getMemory());
                    return platform;
                })
                .forEach(platform -> this.platformPersistence.update(platform.getConsoleName(), platform));
    }

}
