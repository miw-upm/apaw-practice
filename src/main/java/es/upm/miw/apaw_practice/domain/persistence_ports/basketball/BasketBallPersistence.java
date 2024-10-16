package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketBallPersistence {
    BasketBall update(Integer id, BasketBall basketBall);
}
