package es.upm.miw.apaw_practice.adapters.mongodb.car;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.ManufacturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.PieceRepository;


import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;

import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.models.car.Owner;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Arrays;

import java.util.List;

public class CarSeederService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("-------- Car Initial Load --------");

        OwnerEntity[] owners = {
                new OwnerEntity(new Owner("Marcos", "YYSG34", LocalDate.of(1984,8,30))),
                new OwnerEntity(new Owner("Maria", "UCD253", LocalDate.of(1987,5,24))),
                new OwnerEntity(new Owner("Lucia", "GHJFDG", LocalDate.of(1986,5,24))),
                new OwnerEntity(new Owner("Pepe", "34VDSG", LocalDate.of(1978,5,14)))
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));

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
        this.ownerRepository.deleteAll();
        this.manufacturerRepository.deleteAll();
        this.pieceRepository.deleteAll();
        this.carRepository.deleteAll();
    }
}
