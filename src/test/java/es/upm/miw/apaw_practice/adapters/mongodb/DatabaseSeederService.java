package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.CarWorkshopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.CinemaSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.ZooSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final ZooSeederService zooSeederService;
    private final CinemaSeederService cinemaSeederService;
    private final CarWorkshopSeederService carWorkshopSeederService;
    private final UniversitySeederService universitySeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, ZooSeederService zooSeederService, CinemaSeederService cinemaSeederService, CarWorkshopSeederService carWorkshopSeederService, UniversitySeederService universitySeederService) {
        this.shopSeederService = shopSeederService;
        this.zooSeederService = zooSeederService;
        this.cinemaSeederService = cinemaSeederService;
        this.carWorkshopSeederService = carWorkshopSeederService;
        this.universitySeederService = universitySeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.carWorkshopSeederService.seedDatabase();
        this.zooSeederService.seedDatabase();
        this.cinemaSeederService.seedDatabase();
        this.universitySeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.carWorkshopSeederService.deleteAll();
        this.zooSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.cinemaSeederService.deleteAll();
        this.universitySeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
