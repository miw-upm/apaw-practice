package es.upm.miw.apaw_practice.adapters.mongodb;


import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.CarHireSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.CarWorkshopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.CinemaSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.department.DepartmentSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.football.FootballSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.GameWowSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.GymSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.HospitalSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.HotelSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.library.LibrarySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.RestaurantSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.MusicManagerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.PharmacySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.training.TrainingSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.TvSeriesSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.university.UniversitySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.VetClinicSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
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
    private final HotelSeederService hotelSeederService;
    private final Tennis_CourtsSeederService tennis_courtsSeederService;
    private final GymSeederService gymSeederService;
    private final VetClinicSeederService vetClinicSeederService;
    private final FootballSeederService footballSeederService;
    private final CarHireSeederService carHireSeederService;
    private final RestaurantSeederService restaurantSeederService;
    private final MusicManagerSeederService musicManagerSeederService;
    private final EmarketerSeederService emarketerSeederService;
    private final GameWowSeederService gameWowSeederService;
    private final PharmacySeederService pharmacySeederService;
    private final HospitalSeederService hospitalSeederService;
    private final LibrarySeederService librarySeederService;
    private final VideoGameSeederService videoGameSeederService;
    private final DepartmentSeederService departmentSeederService;
    private final TrainingSeederService trainingSeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, ZooSeederService zooSeederService,
                                 CinemaSeederService cinemaSeederService,
                                 CarWorkshopSeederService carWorkshopSeederService,
                                 TvSeriesSeederService tvSeriesSeederService, UniversitySeederService universitySeederService,
                                 HotelSeederService hotelSeederService, Tennis_CourtsSeederService tennis_courtsSeederService,
                                 GymSeederService gymSeederService, VetClinicSeederService vetClinicSeederService,
                                 FootballSeederService footballSeederService, CarHireSeederService carHireSeederService,
                                 RestaurantSeederService restaurantSeederService, MusicManagerSeederService musicManagerSeederService,
                                 EmarketerSeederService emarketerSeederService, GameWowSeederService gameWowSeederService,
                                 HospitalSeederService hospitalSeederService, LibrarySeederService librarySeederService,
                                 DepartmentSeederService departmentSeederService, VideoGameSeederService videoGameSeederService,
                                 PharmacySeederService pharmacySeederService, TrainingSeederService trainingSeederService) {


        this.shopSeederService = shopSeederService;
        this.zooSeederService = zooSeederService;
        this.cinemaSeederService = cinemaSeederService;

        this.carWorkshopSeederService = carWorkshopSeederService;
        this.tvSeriesSeederService = tvSeriesSeederService;
        this.universitySeederService = universitySeederService;
        this.hotelSeederService = hotelSeederService;
        this.tennis_courtsSeederService = tennis_courtsSeederService;
        this.gymSeederService = gymSeederService;
        this.vetClinicSeederService = vetClinicSeederService;
        this.footballSeederService = footballSeederService;
        this.carHireSeederService = carHireSeederService;
        this.restaurantSeederService = restaurantSeederService;
        this.musicManagerSeederService = musicManagerSeederService;
        this.emarketerSeederService = emarketerSeederService;
        this.pharmacySeederService = pharmacySeederService;
        this.hospitalSeederService = hospitalSeederService;
        this.librarySeederService = librarySeederService;
        this.departmentSeederService = departmentSeederService;
        this.videoGameSeederService = videoGameSeederService;
        this.gameWowSeederService = gameWowSeederService;
        this.trainingSeederService = trainingSeederService;

        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
        this.tvSeriesSeederService.seedDatabase();
        this.carWorkshopSeederService.seedDatabase();
        this.zooSeederService.seedDatabase();
        this.cinemaSeederService.seedDatabase();

        this.universitySeederService.seedDatabase();
        this.hotelSeederService.seedDatabase();
        this.tennis_courtsSeederService.seedDatabase();
        this.gymSeederService.seedDatabase();
        this.vetClinicSeederService.seedDatabase();
        this.footballSeederService.seedDatabase();
        this.carHireSeederService.seedDatabase();
        this.restaurantSeederService.seedDatabase();
        this.musicManagerSeederService.seedDatabase();
        this.emarketerSeederService.seedDatabase();
        this.pharmacySeederService.seedDatabase();
        this.gameWowSeederService.seedDatabase();
        this.hospitalSeederService.seedDatabase();
        this.librarySeederService.seedDatabase();
        this.departmentSeederService.seedDatabase();
        this.videoGameSeederService.seedDatabase();
        this.trainingSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
        this.tvSeriesSeederService.deleteAll();
        this.carWorkshopSeederService.deleteAll();
        this.zooSeederService.deleteAll();
        this.universitySeederService.deleteAll();
        this.cinemaSeederService.deleteAll();

        this.universitySeederService.deleteAll();
        this.hotelSeederService.deleteAll();
        this.tennis_courtsSeederService.deleteAll();
        this.gymSeederService.deleteAll();
        this.vetClinicSeederService.deleteAll();
        this.footballSeederService.deleteAll();
        this.carHireSeederService.deleteAll();
        this.restaurantSeederService.deleteAll();
        this.musicManagerSeederService.deleteAll();
        this.emarketerSeederService.deleteAll();
        this.pharmacySeederService.deleteAll();
        this.gameWowSeederService.deleteAll();
        this.hospitalSeederService.deleteAll();
        this.librarySeederService.deleteAll();
        this.departmentSeederService.deleteAll();
        this.videoGameSeederService.deleteAll();
        this.trainingSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
