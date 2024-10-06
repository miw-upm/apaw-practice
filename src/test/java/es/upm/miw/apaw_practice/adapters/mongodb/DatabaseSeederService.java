package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.military.MilitarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.VeterinaryClinicSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final CompetitionSeederService competitionSeederService;
    private final MilitarySeederService militarySeederService;
    private final ArtMuseumSeederService artMuseumSeederService;
    private final VeterinaryClinicSeederService veterinaryClinicSeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, CompetitionSeederService competitionSeederService, MilitarySeederService militarySeederService, ArtMuseumSeederService artMuseumSeederService, VeterinaryClinicSeederService veterinaryClinicSeederService) {
        this.shopSeederService = shopSeederService;
        this.competitionSeederService = competitionSeederService;
        this.militarySeederService = militarySeederService;
        this.artMuseumSeederService = artMuseumSeederService;
        this.veterinaryClinicSeederService = veterinaryClinicSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.competitionSeederService.seedDatabase();
        this.militarySeederService.seedDatabase();
        this.artMuseumSeederService.seedDatabase();
        this.veterinaryClinicSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.competitionSeederService.deleteAll();
        this.militarySeederService.deleteAll();
        this.artMuseumSeederService.deleteAll();
        this.veterinaryClinicSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}