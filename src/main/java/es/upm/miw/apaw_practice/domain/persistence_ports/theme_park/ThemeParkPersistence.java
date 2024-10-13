package es.upm.miw.apaw_practice.domain.persistence_ports.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.ThemePark;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ThemeParkPersistence {
    Stream<ThemePark> readAll();

    void updateThemePark(ThemePark themePark);
}
