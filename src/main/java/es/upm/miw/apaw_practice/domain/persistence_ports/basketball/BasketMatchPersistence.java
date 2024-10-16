package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import org.springframework.stereotype.Repository;

@Repository
public interface BasketMatchPersistence {
    void delete(Integer id);
}
