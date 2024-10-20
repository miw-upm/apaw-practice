package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.BankSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.BasketballSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.BoardgameCafeSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.car.CarSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.HospitalSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.course.CourseSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.DeliveryFoodSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.HotelSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.HotelRetiredSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.MartialArtSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.military.MilitarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.MusicLessonSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.NightLifeSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.ShoppingCenterSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.WushuSportSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.VeterinaryClinicSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.MoviesSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.company.CompanySeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final ShopSeederService shopSeederService;
    private final CompetitionSeederService competitionSeederService;
    private final HospitalSeederService hospitalSeederService;
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
    private final ShoppingCenterSeederService shoppingCenterSeederService;
    private final BasketballSeederService basketballSeederService;
    private final DeliveryFoodSeederService deliveryFoodSeederService;
    private final MoviesSeederService moviesSeederService;
    private final HotelSeederService hotelSeederService;
    private final MusicLessonSeederService musicLessonSeederService;
    private final CourseSeederService courseSeederService;
    private final VideoGameSeederService videoGameSeederService;
    private final GunStoreSeederService gunStoreSeederService;
    private final ECommerceSeederService eCommerceSeederService;
    private final MartialArtSeederService martialArtSeederService;
    private final CompanySeederService companySeederService;

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
            ThemeParkSeederService themeParkSeederService,
            NightLifeSeederService nightLifeSeederService,
            ShoppingCenterSeederService shoppingCenterSeederService,
            BasketballSeederService basketballSeederService,
            DeliveryFoodSeederService deliveryFoodSeederService,
            MoviesSeederService moviesSeederService,
            HotelSeederService hotelSeederService,
            MusicLessonSeederService musicLessonSeederService,
            CourseSeederService courseSeederService,
            VideoGameSeederService videoGameSeederService,
            GunStoreSeederService gunStoreSeederService,
            ECommerceSeederService eCommerceSeederService,
            MartialArtSeederService martialArtSeederService,
            CompanySeederService companySeederService
    ) {
        this.shopSeederService = shopSeederService;
        this.hospitalSeederService = hospitalSeederService;
        this.competitionSeederService = competitionSeederService;
        this.militarySeederService = militarySeederService;
        this.artMuseumSeederService = artMuseumSeederService;
        this.hotelRetiredSeederService = hotelRetiredSeederService;
        this.universitySeederService = universitySeederService;
        this.boardgameCafeSeederService = boardgameCafeSeederService;
        this.wushuSportSeederService = wushuSportSeederService;
        this.veterinaryClinicSeederService = veterinaryClinicSeederService;
        this.bankSeederService = bankSeederService;
        this.carSeederService = carSeederService;
        this.themeParkSeederService = themeParkSeederService;
        this.nightLifeSeederService = nightLifeSeederService;
        this.shoppingCenterSeederService = shoppingCenterSeederService;
        this.basketballSeederService = basketballSeederService;
        this.deliveryFoodSeederService = deliveryFoodSeederService;
        this.moviesSeederService = moviesSeederService;
        this.hotelSeederService = hotelSeederService;
        this.musicLessonSeederService = musicLessonSeederService;
        this.courseSeederService = courseSeederService;
        this.videoGameSeederService = videoGameSeederService;
        this.gunStoreSeederService = gunStoreSeederService;
        this.martialArtSeederService = martialArtSeederService;
        this.eCommerceSeederService = eCommerceSeederService;
        this.companySeederService = companySeederService;

      this.seedDatabase();
    }

    public void seedDatabase() {
        shopSeederService.seedDatabase();
        hospitalSeederService.seedDatabase();
        universitySeederService.seedDatabase();
        competitionSeederService.seedDatabase();
        militarySeederService.seedDatabase();
        artMuseumSeederService.seedDatabase();
        hotelRetiredSeederService.seedDatabase();
        boardgameCafeSeederService.seedDatabase();
        wushuSportSeederService.seedDatabase();
        veterinaryClinicSeederService.seedDatabase();
        bankSeederService.seedDatabase();
        carSeederService.seedDatabase();
        themeParkSeederService.seedDatabase();
        nightLifeSeederService.seedDatabase();
        shoppingCenterSeederService.seedDatabase();
        basketballSeederService.seedDatabase();
        deliveryFoodSeederService.seedDatabase();
        moviesSeederService.seedDatabase();
        hotelSeederService.seedDatabase();
        musicLessonSeederService.seedDatabase();
        courseSeederService.seedDatabase();
        videoGameSeederService.seedDatabase();
        gunStoreSeederService.seedDatabase();
        martialArtSeederService.seedDatabase() ;
        eCommerceSeederService.seedDatabase();
        companySeederService.seedDatabase();
    }

    public void deleteAll() {
        shopSeederService.deleteAll();
        hospitalSeederService.deleteAll();
        universitySeederService.deleteAll();
        competitionSeederService.deleteAll();
        militarySeederService.deleteAll();
        artMuseumSeederService.deleteAll();
        hotelRetiredSeederService.deleteAll();
        boardgameCafeSeederService.deleteAll();
        wushuSportSeederService.deleteAll();
        veterinaryClinicSeederService.deleteAll();
        bankSeederService.deleteAll();
        carSeederService.deleteAll();
        themeParkSeederService.deleteAll();
        nightLifeSeederService.deleteAll();
        shoppingCenterSeederService.deleteAll();
        basketballSeederService.deleteAll();
        deliveryFoodSeederService.deleteAll();
        moviesSeederService.deleteAll();
        hotelSeederService.deleteAll();
        musicLessonSeederService.deleteAll();
        courseSeederService.deleteAll();
        videoGameSeederService.deleteAll();
        gunStoreSeederService.deleteAll();
        martialArtSeederService.deleteAll();
        martialArtSeederService.deleteAll();
        companySeederService.deleteAll();
        this.seedDatabase();
    }

  
    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
