package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    private final PlatformPersistance platformPersistance;

    @Autowired
    public PlatformService(PlatformPersistance platformPersistance) {
        this.platformPersistance = platformPersistance;
    }

    public Platform create(Platform platform) {
        platform.setModel(platform.getModel());
        this.assertConsoleNameNotExist(platform.getConsoleName());
        return this.platformPersistance.create(platform);
    }

    public void assertConsoleNameNotExist(String consoleName) {
        if (this.platformPersistance.existConsoleName(consoleName)) {
            throw new ConflictException("ConsoleName exists: " + consoleName);
        }
    }

}
