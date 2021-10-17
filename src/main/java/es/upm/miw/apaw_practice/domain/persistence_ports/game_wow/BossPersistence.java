package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BossPersistence {
    Stream<Boss> findByEffort(String effort);
    void delete(String description, String effort);
}

