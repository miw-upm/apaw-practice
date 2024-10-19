package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.ShoppingCenterSeederService;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ShopPersistenceMongodbIT {

    @Autowired
    private ShopPersistenceMongodb shopPersistenceMongodb;

    @Autowired
    private ShoppingCenterSeederService shoppingCenterSeederService;

    @Test
    void testReadAll() {
        Optional<Shop> shop = this.shopPersistenceMongodb.readAll()
                .filter(sh -> "shop1".equals(sh.getName()))
                .findFirst();
        assertTrue(shop.isPresent());
        assertNotNull(shop.get().getId());
    }

    @Test
    void testUpdate() {
        Optional<Shop> shop = this.shopPersistenceMongodb.readAll()
                .filter(sh -> "shop2".equals(sh.getName()))
                .findFirst();
        assertTrue(shop.isPresent());
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee("11122233X", "Pedro", "600000008"));
        newEmployees.add(new Employee("11122233Y", "Janna", "600000009"));
        shop.get().setEmployees(newEmployees);
        this.shopPersistenceMongodb.update(shop.get());
        Optional<Shop> newShop = this.shopPersistenceMongodb.readAll()
                .filter(sh -> "shop2".equals(sh.getName()))
                .findFirst();
        assertTrue(newShop.isPresent());
        assertEquals(shop.get().getId(), newShop.get().getId());
        assertNotEquals(shop.get().getEmployees(), newShop.get().getEmployees());
        assertEquals("600000008", newShop.get().getEmployees().get(0).getPhone());
        assertEquals("Janna", newShop.get().getEmployees().get(1).getName());
        shoppingCenterSeederService.deleteAll();
        shoppingCenterSeederService.seedDatabase();
    }

    @Test
    void testFindShopsNameByEmployeeName() {
        List<String> nameShops =
                this.shopPersistenceMongodb.findShopsNameByEmployeeName("Alex").collect(Collectors.toList());
        assertNotNull(nameShops);
        assertNotEquals(0, nameShops.size());
    }
}
