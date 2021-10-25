package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlatformPersistence {

    Stream<Platform> readAll();

    Platform create(Platform platform);

    Platform update(String consoleName, Platform platform);

    Platform read(String consoleName);

    boolean existConsoleName(String consoleName);
}
