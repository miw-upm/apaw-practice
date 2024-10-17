package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketMatchRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketMatchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("basketMatchPersistence")
public class BasketMatchPersistenceMongodb implements BasketMatchPersistence {

    private final BasketMatchRepository basketMatchRepository;

    @Autowired
    public BasketMatchPersistenceMongodb(BasketMatchRepository basketMatchRepository) {
        this.basketMatchRepository = basketMatchRepository;
    }

    @Override
    public void delete(Integer id) {
        this.basketMatchRepository.deleteByMatchId(id);
    }
}
