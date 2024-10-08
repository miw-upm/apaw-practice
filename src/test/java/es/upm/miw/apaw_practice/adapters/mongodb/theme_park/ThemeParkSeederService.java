package es.upm.miw.apaw_practice.adapters.mongodb.theme_park;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.OperatorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.RideRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.ThemeParkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.UserRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.ThemeParkEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ThemeParkSeederService {

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private ThemeParkRepository themeParkRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideRepository rideRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Theme Park Initial Load -----------");
        UserEntity[] users = {
                new UserEntity(new User("712", "C/Rey, 24", LocalDateTime.of(2020,5,23,13,29), true)),
                new UserEntity(new User("14674", "C/Gamepolis, 2", LocalDateTime.of(2021,6,4,10,6), false)),
                new UserEntity(new User("1252", "C/Princesa, 65", LocalDateTime.of(2020,8,12,18,43), true)),
                new UserEntity(new User("41", "C/Alcalá, 12", LocalDateTime.of(2020,7,15,9,55), false)),
        };
        this.userRepository.saveAll(Arrays.asList(users));

        RideEntity[] rides = {
                new RideEntity("Tarántula", "Halloween", LocalDate.of(2018,10,31), 48,  List.of(users[0], users[1]), false),
                new RideEntity("Shambala", "Halloween", LocalDate.of(2020,8,14), 65, List.of(users[0], users[3]), true),
                new RideEntity("Dragon Khan", "Disney", LocalDate.of(2021,7,5), 30, List.of(users[2]), false),
                new RideEntity("El Abismo", "Navidad", LocalDate.of(2022,3,10), 25, List.of(users[2], users[3]), false)
        };
        this.rideRepository.saveAll(Arrays.asList(rides));

        OperatorEntity[] operators = {
                new OperatorEntity("14123H", "Trey", LocalDateTime.of(2012,10,2,9,51), rides[3]),
                new OperatorEntity("65123A", "Jude", LocalDateTime.of(2018,3,10,13,1), rides[2]),
                new OperatorEntity("84523G", "Aidan", LocalDateTime.of(2022,5,12,12,43), rides[0]),
                new OperatorEntity("71235L", "Luca", LocalDateTime.of(2016,8,4,18,22), rides[1])
        };
        this.operatorRepository.saveAll(Arrays.asList(operators));

        ThemeParkEntity[] themeParks = {
                new ThemeParkEntity(true, BigDecimal.valueOf(60.00), Arrays.asList(rides[0], rides[1])),
                new ThemeParkEntity(false, BigDecimal.valueOf(40.00), Arrays.asList(rides[1], rides[2], rides[3])),
        };

        this.themeParkRepository.saveAll(Arrays.asList(themeParks));
    }

    public void deleteAll() {
        this.themeParkRepository.deleteAll();
        this.operatorRepository.deleteAll();
        this.rideRepository.deleteAll();
        this.userRepository.deleteAll();
    }
}
