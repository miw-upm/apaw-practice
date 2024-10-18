package es.upm.miw.apaw_practice.domain.services.e_commerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ShoppingCartECommercePersistence;


@Service
public class ShoppingCartECommerceService {

    private final ShoppingCartECommercePersistence shoppingCartECommercePersistence;

    @Autowired
    public ShoppingCartECommerceService(ShoppingCartECommercePersistence shoppingCartECommercePersistence) {
        this.shoppingCartECommercePersistence = shoppingCartECommercePersistence;
    }

    public void delete(Integer shoppingNum) {
        this.shoppingCartECommercePersistence.delete(shoppingNum);
    }
}
