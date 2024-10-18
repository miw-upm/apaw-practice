package es.upm.miw.apaw_practice.domain.services.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketMatchPersistence;
import org.springframework.stereotype.Service;

@Service
public class BasketMatchService {

    private final BasketMatchPersistence basketMatchPersistence;

    public BasketMatchService(BasketMatchPersistence basketMatchPersistence) {
        this.basketMatchPersistence = basketMatchPersistence;
    }

    public void delete(Integer id) {
        this.basketMatchPersistence.delete(id);
    }

    public BasketMatch updateAddress(Integer matchId, String address) {
        return this.basketMatchPersistence.updateAddress(matchId,address);
    }
}
