package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.EmployeeShoppingCenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.ShopRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.EmployeeShoppingCenterEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ShopEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ShopPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("shopPersistence")
public class ShopPersistenceMongodb implements ShopPersistence {

    private final ShopRepository shopRepository;
    private final EmployeeShoppingCenterRepository employeeShoppingCenterRepository;

    @Autowired
    public ShopPersistenceMongodb(ShopRepository shopRepository, EmployeeShoppingCenterRepository employeeShoppingCenterRepository) {
        this.shopRepository = shopRepository;
        this.employeeShoppingCenterRepository = employeeShoppingCenterRepository;
    }

    @Override
    public Stream<Shop> readAll() {
        return this.shopRepository.findAll().stream().map(ShopEntity::toShop);
    }

    @Override
    public Shop readById(String id) {
        return this.shopRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Shop id:" + id))
                .toShop();
    }

    @Override
    public Shop update(Shop shop) {
        ShopEntity shopEntity = this.shopRepository
                .findById(shop.getId())
                .orElseThrow(() -> new NotFoundException("Shop id:" + shop.getId()));
        List<EmployeeShoppingCenterEntity> employeeEntities = shop.getEmployees().stream()
                .map(EmployeeShoppingCenterEntity::new).collect(Collectors.toList());
        shopEntity.setEmployees(employeeEntities);
        this.employeeShoppingCenterRepository.saveAll(employeeEntities);
        return this.shopRepository.save(shopEntity).toShop();
    }
}
