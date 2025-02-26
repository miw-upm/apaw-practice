package es.upm.miw.apawpractice.domain.persistenceports.shop;

import es.upm.miw.apawpractice.domain.models.shop.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ShoppingCartPersistence {

    Stream<ShoppingCart> readAll();

    ShoppingCart readById(String id);

    ShoppingCart update(ShoppingCart shoppingCart);
}
