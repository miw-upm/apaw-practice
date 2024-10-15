package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketMatchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketSeasonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketSeasonEntity;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketSeasonPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("basketSeasonPersistence")
public class BasketSeasonPersistenceMongodb implements BasketSeasonPersistence {

    private final BasketSeasonRepository basketSeasonRepository;
    private final BasketMatchRepository basketMatchRepository;

    public BasketSeasonPersistenceMongodb(BasketSeasonRepository basketSeasonRepository, BasketMatchRepository basketMatchRepository) {
        this.basketSeasonRepository = basketSeasonRepository;
        this.basketMatchRepository = basketMatchRepository;
    }

    @Override
    public BasketSeason create(BasketSeason basketSeason) {
        List<Integer> idList = basketSeason.getBasketMatches().stream()
                .map(BasketMatch::getId)
                .toList();
        List<BasketMatchEntity> basketMatchesEntities = this.basketMatchRepository.findByMatchIdIn(idList);
        return this.basketSeasonRepository
                .save(new BasketSeasonEntity(basketSeason.getId(),basketSeason.getStartYear(),
                        basketSeason.getEndYear(), basketSeason.getLeague(), basketMatchesEntities))
                .toBasketSeason();
    }
}
