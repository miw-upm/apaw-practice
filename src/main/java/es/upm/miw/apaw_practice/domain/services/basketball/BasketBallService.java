package es.upm.miw.apaw_practice.domain.services.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketBallPersistence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketBallService {

    private final BasketBallPersistence basketBallPersistence;

    public BasketBallService(BasketBallPersistence basketBallPersistence) {
        this.basketBallPersistence = basketBallPersistence;
    }

    public BasketBall update(Integer id, BasketBall basketBall) {
        return this.basketBallPersistence.update(id, basketBall);
    }

    public List<String> getDistinctBrands(String league, String playerName){
        return this.basketBallPersistence.getDistinctBrands(league, playerName);
    }

    public BigDecimal getTotalPrice(Integer dorsal){
        return this.basketBallPersistence.getTotalPrice(dorsal);
    }
}
