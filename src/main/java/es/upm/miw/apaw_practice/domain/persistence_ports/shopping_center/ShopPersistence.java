package es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ShopPersistence {

    Stream<Shop> readAll();

    Shop readById(String id);
}
