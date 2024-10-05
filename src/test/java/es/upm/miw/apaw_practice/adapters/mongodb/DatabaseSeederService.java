package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.military.MilitarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final CompetitionSeederService competitionSeederService;
    private final MilitarySeederService militarySeederService;
    private final ArtMuseumSeederService artMuseumSeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, CompetitionSeederService competitionSeederService, MilitarySeederService militarySeederService, ArtMuseumSeederService artMuseumSeederService) {
        this.shopSeederService = shopSeederService;
        this.competitionSeederService = competitionSeederService;
        this.militarySeederService = militarySeederService;
        this.artMuseumSeederService = artMuseumSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.competitionSeederService.seedDatabase();
        this.militarySeederService.seedDatabase();
        this.artMuseumSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.competitionSeederService.deleteAll();
        this.militarySeederService.deleteAll();
        this.artMuseumSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
