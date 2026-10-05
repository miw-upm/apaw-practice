package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.carreservation.postgres.CarEntity;
import es.upm.miw.apaw.adapters.out.carreservation.postgres.CarRepository;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.FuelType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class CarReservationSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "11111111-2222-3333-4444-55556666";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Car CAR_0 = Car.builder()
            .id(ID_0)
            .brand("Seat")
            .model("Ibiza")
            .licensePlate("1234BBB")
            .registrationDate(LocalDate.of(2021, 1, 15))
            .numberOfSeats(5)
            .fuelType(FuelType.PETROL)
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Car CAR_1 = Car.builder()
            .id(ID_1)
            .brand("Toyota")
            .model("Yaris")
            .licensePlate("5678CCC")
            .registrationDate(LocalDate.of(2022, 5, 20))
            .numberOfSeats(5)
            .fuelType(FuelType.HYBRID)
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final Car CAR_2 = Car.builder()
            .id(ID_2)
            .brand("Tesla")
            .model("Model 3")
            .licensePlate("9012DDD")
            .registrationDate(LocalDate.of(2023, 10, 10))
            .numberOfSeats(5)
            .fuelType(FuelType.ELECTRIC)
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final Car CAR_3 = Car.builder()
            .id(ID_3)
            .brand("Volkswagen")
            .model("Golf")
            .licensePlate("3456EEE")
            .registrationDate(LocalDate.of(2020, 3, 12))
            .numberOfSeats(5)
            .fuelType(FuelType.DIESEL)
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final Car CAR_4 = Car.builder()
            .id(ID_4)
            .brand("Peugeot")
            .model("2008")
            .licensePlate("7890FFF")
            .registrationDate(LocalDate.of(2024, 2, 1))
            .numberOfSeats(5)
            .fuelType(FuelType.HYBRID)
            .build();

    private final CarRepository carRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA (Car) -----------");
        List<CarEntity> cars = List.of(CAR_0, CAR_1, CAR_2, CAR_3, CAR_4).stream()
                .filter(car -> !this.carRepository.existsById(car.getId()))
                .map(CarEntity::new)
                .toList();
        this.carRepository.saveAll(cars);
        log.warn("        ------- cars: {} added", cars.size());
    }
}