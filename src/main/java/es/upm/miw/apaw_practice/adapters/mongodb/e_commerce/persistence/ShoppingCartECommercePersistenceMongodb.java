package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.ShoppingCartECommercePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ShoppingCartECommercePersistence")
public class ShoppingCartECommercePersistenceMongodb implements ShoppingCartECommercePersistence {

    private final ECommerceShoppingCartRepository ecommerceShoppingCartRepository;

    @Autowired
    public ShoppingCartECommercePersistenceMongodb(ECommerceShoppingCartRepository ecommerceShoppingCartRepository) {
        this.ecommerceShoppingCartRepository = ecommerceShoppingCartRepository;
    }

    @Override
    public void delete(Integer shoppingNum) {
        ShoppingCartECommerceEntity shoppingCart = this.ecommerceShoppingCartRepository.findByShoppingNum(shoppingNum)
                .orElseThrow(() -> new NotFoundException("Shopping Cart number: " + shoppingNum));

        this.ecommerceShoppingCartRepository.delete(shoppingCart);
    }
}
