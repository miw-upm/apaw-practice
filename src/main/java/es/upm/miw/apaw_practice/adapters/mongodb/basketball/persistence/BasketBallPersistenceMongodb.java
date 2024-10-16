package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketBallRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketMatchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketBallEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketBallPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository("basketBallPersistence")
public class BasketBallPersistenceMongodb implements BasketBallPersistence {

    private final BasketBallRepository basketBallRepository;
    private final BasketMatchRepository basketMatchRepository;

    public BasketBallPersistenceMongodb(BasketBallRepository basketBallRepository, BasketMatchRepository basketMatchRepository) {
        this.basketBallRepository = basketBallRepository;
        this.basketMatchRepository = basketMatchRepository;
    }

    @Override
    public BasketBall update(Integer id, BasketBall basketBall) {
        BasketBallEntity basketBallEntity = this.basketBallRepository.findByBallId(id)
                .orElseThrow(() -> new NotFoundException("Basket Ball id:" + id));
        BeanUtils.copyProperties(basketBall, basketBallEntity);
        BasketMatchEntity basketMatchEntity = this.basketMatchRepository.findByMatchId(basketBall.getBasketMatch().getId())
                .orElseThrow(() -> new NotFoundException("Match ID:" + basketBall.getBasketMatch().getId()));
        basketBallEntity.setBasketMatchEntity(basketMatchEntity);
        return basketBallRepository.save(basketBallEntity).toBasketBall();
    }
}
