package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.BankSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.BoardgameCafeSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.car.CarSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.HospitalSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.HotelRetiredSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.military.MilitarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.NightLifeSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.WushuSportSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.VeterinaryClinicSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final HospitalSeederService hospitalSeederService;
    private final CompetitionSeederService competitionSeederService;
    private final MilitarySeederService militarySeederService;
    private final ArtMuseumSeederService artMuseumSeederService;
    private final HotelRetiredSeederService hotelRetiredSeederService;
    private final UniversitySeederService universitySeederService;
    private final BoardgameCafeSeederService boardgameCafeSeederService;
    private final WushuSportSeederService wushuSportSeederService;
    private final VeterinaryClinicSeederService veterinaryClinicSeederService;
    private final BankSeederService bankSeederService;
    private final CarSeederService carSeederService;
    private final ThemeParkSeederService themeParkSeederService;
    private final NightLifeSeederService nightLifeSeederService;

    @Autowired
    public DatabaseSeederService(
            ShopSeederService shopSeederService,
            HospitalSeederService hospitalSeederService,
            CompetitionSeederService competitionSeederService,
            MilitarySeederService militarySeederService,
            ArtMuseumSeederService artMuseumSeederService,
            HotelRetiredSeederService hotelRetiredSeederService,
            UniversitySeederService universitySeederService,
            BoardgameCafeSeederService boardgameCafeSeederService,
            WushuSportSeederService wushuSportSeederService,
            VeterinaryClinicSeederService veterinaryClinicSeederService,
            BankSeederService bankSeederService,
            CarSeederService carSeederService,
            ThemeParkSeederService themeParkSeederService, NightLifeSeederService nightLifeSeederService
    ) {
        this.shopSeederService = shopSeederService;
        this.hospitalSeederService=hospitalSeederService;
        this.universitySeederService = universitySeederService;
        this.competitionSeederService = competitionSeederService;
        this.militarySeederService = militarySeederService;
        this.artMuseumSeederService = artMuseumSeederService;
        this.hotelRetiredSeederService = hotelRetiredSeederService;
        this.boardgameCafeSeederService = boardgameCafeSeederService;
        this.wushuSportSeederService = wushuSportSeederService;
        this.veterinaryClinicSeederService = veterinaryClinicSeederService;
        this.bankSeederService = bankSeederService;
        this.carSeederService = carSeederService;
        this.themeParkSeederService = themeParkSeederService;
        this.nightLifeSeederService = nightLifeSeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.hospitalSeederService.seedDatabase();
        this.universitySeederService.seedDatabase();
        this.competitionSeederService.seedDatabase();
        this.militarySeederService.seedDatabase();
        this.artMuseumSeederService.seedDatabase();
        this.hotelRetiredSeederService.seedDatabase();
        this.boardgameCafeSeederService.seedDatabase();
        this.wushuSportSeederService.seedDatabase();
        this.veterinaryClinicSeederService.seedDatabase();
         this.bankSeederService.seedDatabase();
        this.carSeederService.seedDatabase();
        this.themeParkSeederService.seedDatabase();
        this.nightLifeSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.hospitalSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.competitionSeederService.deleteAll();
        this.militarySeederService.deleteAll();
        this.artMuseumSeederService.deleteAll();
        this.hotelRetiredSeederService.deleteAll();
        this.boardgameCafeSeederService.deleteAll();
        this.wushuSportSeederService.deleteAll();
        this.veterinaryClinicSeederService.deleteAll();
        this.bankSeederService.deleteAll();
        this.carSeederService.deleteAll();
        this.themeParkSeederService.deleteAll();
        this.nightLifeSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}