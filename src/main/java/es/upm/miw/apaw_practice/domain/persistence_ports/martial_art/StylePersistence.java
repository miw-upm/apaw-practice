package es.upm.miw.apaw_practice.domain.persistence_ports.martial_art;

import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface StylePersistence {

    Stream<Style> readAll();

    Style create(Style style);

    Style update(String name, Style style);

    Style read(String name);

    boolean existsByName(String name);

    void delete(String name);
}
