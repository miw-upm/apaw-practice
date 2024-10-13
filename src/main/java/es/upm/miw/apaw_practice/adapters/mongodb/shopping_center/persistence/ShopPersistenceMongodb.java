package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.ShopRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ShopEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ShopPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("shopPersistence")
public class ShopPersistenceMongodb implements ShopPersistence {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopPersistenceMongodb(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Stream<Shop> readAll() {
        return this.shopRepository.findAll().stream().map(ShopEntity::toShop);
    }
}
