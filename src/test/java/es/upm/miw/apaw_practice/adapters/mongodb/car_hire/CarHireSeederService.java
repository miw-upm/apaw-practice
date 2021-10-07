package es.upm.miw.apaw_practice.adapters.mongodb.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.ModelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.BookingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.ModelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CarHireSeederService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RenterRepository renterRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelRepository modelRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Car Hire Initial Load -----------");
        RenterEntity[] renters = {
                new RenterEntity(new Renter("Pablo", "51435421N", true)),
                new RenterEntity(new Renter("Alejandro", "84315984J", false)),
                new RenterEntity(new Renter("Manuel", "29681563D", true)),
        };
        this.renterRepository.saveAll(Arrays.asList(renters));
        VehicleEntity[] vehicles = {
                new VehicleEntity(new Vehicle("VSSZZZ6KZ1R149943", new BigDecimal("50"), 25400, true)),
                new VehicleEntity(new Vehicle("WVGZZZ5NZJM131395", new BigDecimal("50"), 32000, true)),
                new VehicleEntity(new Vehicle("JCPCBL6HSCX110002", new BigDecimal("30"), 45000, false)),
                new VehicleEntity(new Vehicle("GYWKAS8AHBD284620", new BigDecimal("30"), 52000, true)),
        };
        this.vehicleRepository.saveAll(Arrays.asList(vehicles));
        ModelEntity[] models = {
                new ModelEntity(new Model("Opel Insignia", "Tipo Berlina, manual", 140, List.of(vehicles[0].toVehicle()))),
                new ModelEntity(new Model("Opel Insignia", "Tipo Berlina, automático", 130, List.of(vehicles[1].toVehicle()))),
                new ModelEntity(new Model("Seat Ibiza", "Tipo Compacto, manual", 90, List.of(vehicles[2].toVehicle(), vehicles[3].toVehicle()))),
        };
        this.modelRepository.saveAll(Arrays.asList(models));
        BookingEntity[] bookings = {
                new BookingEntity(List.of(vehicles[0]), renters[0],"1403", 12),
                new BookingEntity(List.of(vehicles[1]), renters[1],"1404", 7),
                new BookingEntity(List.of(vehicles[2]), renters[2],"1405", 10),
                new BookingEntity(List.of(vehicles[3]), renters[2],"1406", 8),
        };
        this.bookingRepository.saveAll(Arrays.asList(bookings));
    }

    public void deleteAll() {
        this.renterRepository.deleteAll();
        this.vehicleRepository.deleteAll();
        this.modelRepository.deleteAll();
        this.bookingRepository.deleteAll();
    }

}
