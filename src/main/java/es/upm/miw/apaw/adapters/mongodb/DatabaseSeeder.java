package es.upm.miw.apaw.adapters.mongodb;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.AirportSeeder;
import es.upm.miw.apaw.adapters.mongodb.shop.daos.ShopSeeder;
import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
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
    private final VehicleSeeder vehicleSeeder;

    @Autowired
    public DatabaseSeeder(ShopSeeder shopSeeder, AirportSeeder airportSeeder, VehicleSeeder vehicleSeeder) {
        this.shopSeeder = shopSeeder;
        this.airportSeeder = airportSeeder;
        this.vehicleSeeder = vehicleSeeder;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeeder.seedDatabase();
        this.airportSeeder.seedDatabase();
        this.vehicleSeeder.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeeder.deleteAll();
        this.airportSeeder.deleteAll();
        this.vehicleSeeder.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        log.warn("------- Deleted All -----------");
        this.seedDatabase();
    }
}
