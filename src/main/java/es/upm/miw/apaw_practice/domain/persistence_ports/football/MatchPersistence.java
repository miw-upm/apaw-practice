package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchPersistence {
    Integer delete(Integer round);
}
