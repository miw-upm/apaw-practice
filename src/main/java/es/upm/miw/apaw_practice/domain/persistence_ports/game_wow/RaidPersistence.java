package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;

import java.util.stream.Stream;

public interface RaidPersistence {
    Raid readById(String id);
    Raid create(Raid raid);
    Raid update(Raid raid);
    Stream<Raid> readAll();
}