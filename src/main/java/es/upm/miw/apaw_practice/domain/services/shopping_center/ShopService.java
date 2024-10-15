package es.upm.miw.apaw_practice.domain.services.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ShopPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ShopService {

    private final ShopPersistence shopPersistence;

    @Autowired
    public ShopService(ShopPersistence shopPersistence) {
        this.shopPersistence = shopPersistence;
    }

    public Stream<Shop> findAllShops() {
        return this.shopPersistence.readAll();
    }

    public Shop updateEmployees(String id, List<Employee> employeeList) {
        Shop shop = this.shopPersistence.readById(id);
        shop.setEmployees(employeeList);
        return this.shopPersistence.update(shop);
    }
}
