package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DropPersistence {
    Stream<Drop> readAll();
}
