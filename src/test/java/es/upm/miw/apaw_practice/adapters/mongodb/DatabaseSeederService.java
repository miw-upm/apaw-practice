package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.CarWorkshopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final CarWorkshopSeederService carWorkshopSeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, CarWorkshopSeederService carWorkshopSeederService) {
        this.shopSeederService = shopSeederService;
        this.carWorkshopSeederService = carWorkshopSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.carWorkshopSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.carWorkshopSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
