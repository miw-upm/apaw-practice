package es.upm.miw.apaw.adapters.mongodb;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.AirportSeeder;
import es.upm.miw.apaw.adapters.mongodb.shop.daos.ShopSeeder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class DatabaseSeeder {

    private final ShopSeeder shopSeeder;
    private final AirportSeeder airportSeeder;

    @Autowired
    public DatabaseSeeder(ShopSeeder shopSeeder, AirportSeeder airportSeeder) {
        this.shopSeeder = shopSeeder;
        this.airportSeeder = airportSeeder;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeeder.seedDatabase();
        this.airportSeeder.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeeder.deleteAll();
        this.airportSeeder.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        log.warn("------- Deleted All -----------");
        this.seedDatabase();
    }
}
