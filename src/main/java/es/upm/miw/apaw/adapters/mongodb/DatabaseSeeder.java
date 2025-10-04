package es.upm.miw.apaw.adapters.mongodb;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.AirportSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.shop.daos.ShopSeeder;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.SportsAcademySeeder;
import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiarySeeder;
import es.upm.miw.apaw.adapters.mongodb.university.daos.UniversitySeeder;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameSeeder;
import es.upm.miw.apaw.adapters.mongodb.winery.daos.WinerySeeder;
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
    private final ApiarySeeder apiarySeeder;
    private final RecruitingSeeder recruitingSeeder;
    private final WinerySeeder winerySeeder;
    private final UniversitySeeder universitySeeder;
    private final SportsAcademySeeder sportsAcademySeeder;
    private final VideogameSeeder videogameSeeder;

    @Autowired
    public DatabaseSeeder(
            ShopSeeder shopSeeder,
            AirportSeeder airportSeeder,
            VehicleSeeder vehicleSeeder,
            ApiarySeeder apiarySeeder,
            RecruitingSeeder recruitingSeeder,
            WinerySeeder winerySeeder,
            UniversitySeeder universitySeeder,
            SportsAcademySeeder sportsAcademySeeder,
            VideogameSeeder videogameSeeder
            ) {
        this.shopSeeder = shopSeeder;
        this.airportSeeder = airportSeeder;
        this.vehicleSeeder = vehicleSeeder;
        this.apiarySeeder = apiarySeeder;
        this.recruitingSeeder = recruitingSeeder;
        this.winerySeeder = winerySeeder;
        this.universitySeeder = universitySeeder;
        this.sportsAcademySeeder = sportsAcademySeeder;
        this.videogameSeeder = videogameSeeder;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeeder.seedDatabase();
        this.airportSeeder.seedDatabase();
        this.vehicleSeeder.seedDatabase();
        this.apiarySeeder.seedDatabase();
        this.recruitingSeeder.seedDatabase();
        this.winerySeeder.seedDatabase();
        this.universitySeeder.seedDatabase();
        this.sportsAcademySeeder.seedDatabase();
        this.videogameSeeder.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeeder.deleteAll();
        this.airportSeeder.deleteAll();
        this.vehicleSeeder.deleteAll();
        this.apiarySeeder.deleteAll();
        this.recruitingSeeder.deleteAll();
        this.winerySeeder.deleteAll();
        this.universitySeeder.deleteAll();
        this.sportsAcademySeeder.deleteAll();
        this.videogameSeeder.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        log.warn("------- Deleted All -----------");
        this.seedDatabase();
    }
}
