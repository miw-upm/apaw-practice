package es.upm.miw.apaw_practice.domain.services.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ShopPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private final ShopPersistence shopPersistence;

    @Autowired
    public ShopService(ShopPersistence shopPersistence) {
        this.shopPersistence = shopPersistence;
    }

    public Shop findById(String id) {
        return this.shopPersistence.readById(id);
    }
}
