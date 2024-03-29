package es.upm.miw.apaw_practice.adapters.mongodb.formula_one;

import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.daos.DriverRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.daos.EngineManufacturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.daos.RaceRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.daos.TeamRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.entities.DriverEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.entities.EngineManufacturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.entities.RaceEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.formula_one.entities.TeamEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class FormulaOneSeederService {

    @Autowired
    private EngineManufacturerRepository engineManufacturerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RaceRepository raceRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Formula One Initial Load -----------");
        EngineManufacturerEntity[] engineManufacturers = {
                new EngineManufacturerEntity("Honda", "Japan", 6),
                new EngineManufacturerEntity("Mercedes", "United Kingdom", 12)
        };
        this.engineManufacturerRepository.saveAll(Arrays.asList(engineManufacturers));

        DriverEntity[] drivers = {
                new DriverEntity(1, "Max Verstappen", "Netherlands"),
                new DriverEntity(11, "Sergio Pérez", "Mexico"),
                new DriverEntity(14, "Fernando Alonso", "Spain"),
                new DriverEntity(18, "Lance Stroll", "Canada"),
                new DriverEntity(44, "Lewis Hamilton", "United Kingdom"),
                new DriverEntity(63, "George Russell", "United Kingdom"),
                new DriverEntity(4, "Lando Norris", "United Kingdom"),
                new DriverEntity(81, "Oscar Piastri", "Australia"),
                new DriverEntity(23, "Alexander Albon", "Thailand")
        };
        drivers[0].setPoints(224.5F);
        drivers[1].setPoints(108.0F);
        this.driverRepository.saveAll(Arrays.asList(drivers));

        TeamEntity[] teams = {
                new TeamEntity("Red Bull Racing", "Austria", Arrays.asList(drivers[0], drivers[1]), engineManufacturers[0]),
                new TeamEntity("Aston Martin", "United Kingdom", Arrays.asList(drivers[2], drivers[3]), engineManufacturers[1]),
                new TeamEntity("Mercedes", "Germany", Arrays.asList(drivers[4], drivers[5]), engineManufacturers[1]),
                new TeamEntity("McLaren", "United Kingdom", Arrays.asList(drivers[6], drivers[7]), engineManufacturers[1]),
                new TeamEntity("Williams", "United Kingdom", Collections.singletonList(drivers[8]), engineManufacturers[1])

        };
        this.teamRepository.saveAll(Arrays.asList(teams));

        RaceEntity[] races = {
                new RaceEntity("Albert Park", "Australia", LocalDate.of(2023, 4, 2), List.of(drivers)),
                new RaceEntity("Hermanos Rodríguez", "Mexico", LocalDate.of(2023, 10, 29), List.of(drivers)),
                new RaceEntity("Silverstone", "United Kingdom", LocalDate.of(2023, 7, 16), List.of(drivers)),
                new RaceEntity("Las Vegas", "USA", LocalDate.of(2023, 10, 8), Arrays.asList(drivers[0], drivers[1])),
                new RaceEntity("Miami", "USA", LocalDate.of(2023, 5, 14), Arrays.asList(drivers[0], drivers[2], drivers[6])),
                new RaceEntity("Red Bull Ring", "Austria", LocalDate.of(2023, 5, 14), Collections.singletonList(drivers[0]))
        };
        this.raceRepository.saveAll(Arrays.asList(races));
    }

    public void deleteAll() {
        this.engineManufacturerRepository.deleteAll();
        this.driverRepository.deleteAll();
        this.teamRepository.deleteAll();
        this.raceRepository.deleteAll();
    }

}
