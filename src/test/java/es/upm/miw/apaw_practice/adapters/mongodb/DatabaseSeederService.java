package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.HotelRetiredSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.military.MilitarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final CompetitionSeederService competitionSeederService;
    private final MilitarySeederService militarySeederService;
    private final ArtMuseumSeederService artMuseumSeederService;
    private final HotelRetiredSeederService hotelRetiredSeederService;
    private final UniversitySeederService universitySeederService;

    @Autowired
    public DatabaseSeederService(
            ShopSeederService shopSeederService,
            CompetitionSeederService competitionSeederService,
            MilitarySeederService militarySeederService,
            ArtMuseumSeederService artMuseumSeederService,
            HotelRetiredSeederService hotelRetiredSeederService,
            UniversitySeederService universitySeederService
    ) {
        this.shopSeederService = shopSeederService;
        this.universitySeederService = universitySeederService;
        this.competitionSeederService = competitionSeederService;
        this.militarySeederService = militarySeederService;
        this.artMuseumSeederService = artMuseumSeederService;
        this.hotelRetiredSeederService = hotelRetiredSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.universitySeederService.seedDatabase();
        this.competitionSeederService.seedDatabase();
        this.militarySeederService.seedDatabase();
        this.artMuseumSeederService.seedDatabase();
        this.hotelRetiredSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.competitionSeederService.deleteAll();
        this.militarySeederService.deleteAll();
        this.artMuseumSeederService.deleteAll();
        this.hotelRetiredSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
