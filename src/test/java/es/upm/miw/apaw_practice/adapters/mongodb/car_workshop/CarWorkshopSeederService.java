package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreSpecificationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CarWorkshopSeederService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private TyreRepository tyreRepository;
    @Autowired
    private TyreSpecificationRepository tyreSpecsRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("-- Car Workshop Initial Load --");
        TyreSpecificationEntity[] tyreSpecs = {
                new TyreSpecificationEntity(205, 16, "100Y"),
                new TyreSpecificationEntity(205, 16, "95Z"),
                new TyreSpecificationEntity(225, 17, "90T"),
                new TyreSpecificationEntity(215, 17, "90T")
        };
        this.tyreSpecsRepository.saveAll(Arrays.asList(tyreSpecs));
        TyreEntity[] tyres = {
                new TyreEntity("Hankook", "Ventus Prime", new BigDecimal("55.00"), tyreSpecs[0]),
                new TyreEntity("Hankook", "Kinergy", new BigDecimal("79.99"), tyreSpecs[1]),
                new TyreEntity("Michelin", "Primacy", new BigDecimal("109.00"), tyreSpecs[1]),
                new TyreEntity("Bridgestone", "Turanza", new BigDecimal("150.00"), tyreSpecs[2])
        };
        this.tyreRepository.saveAll(Arrays.asList(tyres));
        OwnerEntity[] owners = {
                new OwnerEntity("00000000Z", "John Doe"),
                new OwnerEntity("99999999A", "Jane Doe")
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));
        CarEntity[] cars = {
                new CarEntity("1111AAA", true, owners[0], List.of(tyreSpecs[0], tyreSpecs[1])),
                new CarEntity("2222BBB", false, owners[0], List.of(tyreSpecs[3])),
                new CarEntity("3333CCC", false, owners[1], List.of(tyreSpecs[2], tyreSpecs[3]))
        };
        this.carRepository.saveAll(Arrays.asList(cars));
    }

    public void deleteAll() {
        this.carRepository.deleteAll();
        this.tyreRepository.deleteAll();
        this.tyreSpecsRepository.deleteAll();
    }
}
