package es.upm.miw.apaw_practice.adapters.mongodb.car;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.ManufacturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.PieceRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CarSeederService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerCarRepository ownerCarRepository;
    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("-------- Car Initial Load --------");

        OwnerCarEntity[] owners = {
                new OwnerCarEntity(new OwnerCar("Marcos", "YYSG34", LocalDate.of(1984,8,30))),
                new OwnerCarEntity(new OwnerCar("Maria", "UCD253", LocalDate.of(1987,5,24))),
                new OwnerCarEntity(new OwnerCar("Lucia", "GHJFDG", LocalDate.of(1986,5,24))),
                new OwnerCarEntity(new OwnerCar("Pepe", "34VDSG", LocalDate.of(1978,5,14)))
        };
        this.ownerCarRepository.saveAll(Arrays.asList(owners));

        ManufacturerEntity[] manufactures = {
                new ManufacturerEntity(new Manufacturer("Tesla", "USA", 2000)),
                new ManufacturerEntity(new Manufacturer("Ford", "USA", 400)),
                new ManufacturerEntity(new Manufacturer("Renault", "France", 1000)),
                new ManufacturerEntity(new Manufacturer("Seat", "Spain", 4000))
        };
        this.manufacturerRepository.saveAll(Arrays.asList(manufactures));

        PieceEntity[] pieces = {
                new PieceEntity("WSDF", "Engine", new BigDecimal(200), List.of(manufactures[0])),
                new PieceEntity("SFGE", "Door", new BigDecimal(400), List.of(manufactures[1])),
                new PieceEntity("FHGT", "Window", new BigDecimal(200), List.of(manufactures[2])),
                new PieceEntity("RTHN", "Seat", new BigDecimal(500), List.of(manufactures[3])),
        };
        this.pieceRepository.saveAll(Arrays.asList(pieces));

        CarEntity[] cars = {
                new CarEntity("Model 3", true,new BigDecimal(40000), owners[0], List.of(pieces[0])),
                new CarEntity("Mustang", false,new BigDecimal(50000), owners[1], List.of(pieces[1])),
                new CarEntity("Scenic", false,new BigDecimal(30000), owners[2], List.of(pieces[2])),
                new CarEntity("Leon", false,new BigDecimal(25000), owners[3], List.of(pieces[3])),
        };
        this.carRepository.saveAll(Arrays.asList(cars));
    }

    public void deleteAll() {
        this.ownerCarRepository.deleteAll();
        this.manufacturerRepository.deleteAll();
        this.pieceRepository.deleteAll();
        this.carRepository.deleteAll();
    }
}
