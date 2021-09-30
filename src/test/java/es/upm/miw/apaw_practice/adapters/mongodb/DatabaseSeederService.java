package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.CarWorkshopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.CinemaSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.TvSeriesSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.ZooSeederService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final TvSeriesSeederService tvSeriesSeederService;
    private final ZooSeederService zooSeederService;
    private final CinemaSeederService cinemaSeederService;
    private final CarWorkshopSeederService carWorkshopSeederService;
    private final UniversitySeederService universitySeederService;
    private final Tennis_CourtsSeederService tennis_courtsSeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, ZooSeederService zooSeederService,
                                 CinemaSeederService cinemaSeederService, CarWorkshopSeederService carWorkshopSeederService,
                                 TvSeriesSeederService tvSeriesSeederService, UniversitySeederService universitySeederService,
                                 Tennis_CourtsSeederService tennis_courtsSeederService) {
        this.shopSeederService = shopSeederService;
        this.zooSeederService = zooSeederService;
        this.cinemaSeederService = cinemaSeederService;
        this.carWorkshopSeederService = carWorkshopSeederService;
        this.tvSeriesSeederService = tvSeriesSeederService;
        this.universitySeederService = universitySeederService;
        this.tennis_courtsSeederService = tennis_courtsSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.tvSeriesSeederService.seedDatabase();
        this.carWorkshopSeederService.seedDatabase();
        this.zooSeederService.seedDatabase();
        this.cinemaSeederService.seedDatabase();
        this.universitySeederService.seedDatabase();
        this.tennis_courtsSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.tvSeriesSeederService.deleteAll();
        this.carWorkshopSeederService.deleteAll();
        this.zooSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.cinemaSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.tennis_courtsSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
