package es.upm.miw.apaw_practice.adapters.mongodb.fruitshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.fruitshop.FruitShopSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.fruitShop.FruitShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class FruitShopPersistenceMongdbIT {
    @Autowired
    private FruitShopPersistenceMongdb fruitShopPersistenceMongdb;

    @Autowired
    private FruitShopSeederService fruitShopSeederService;

    @BeforeEach
    void CleanUpDB() {
        this.fruitShopSeederService.deleteAll();
        this.fruitShopSeederService.seedDatabase();
    }

    @Test
    void testUpdateFruitShopAddress() {
        FruitShop fruitShop = this.fruitShopPersistenceMongdb.updateAddress("Fruit Test");
        assertNotNull(fruitShop);
        assertEquals("Fruit Test", fruitShop.getName());
        assertEquals("New Address", fruitShop.getAddress());
    }

    @Test
    void testUpdateFruitShopException() {
        assertThrows(NotFoundException.class, () ->this.fruitShopPersistenceMongdb.updateAddress("test"));
    }

    @Test
    void testFindAddressByFruitSpecie(){
        Stream<FruitShop> fruitShopStream = this.fruitShopPersistenceMongdb.findAddressByFruitSpecie("Normal");
        List<FruitShop> fruitShopList = fruitShopStream.collect(Collectors.toList());

        assertNotNull(fruitShopList);
        assertEquals(1, fruitShopList.size());

        FruitShop fruitShop = fruitShopList.get(0);
        assertEquals("Malaga", fruitShop.getAddress());

    }


}
