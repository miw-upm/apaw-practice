package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.adapters.out.carreservation.postgres.CarEntity;
import es.upm.miw.apaw.adapters.out.carreservation.postgres.ReservationEntity;
import es.upm.miw.apaw.adapters.out.carreservation.postgres.ReservationRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.CarSeatsAndFuelUpdate;
import es.upm.miw.apaw.domain.model.carreservation.FuelType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CarReservationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class CarServiceIT {

    @Autowired
    private CarService carService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.carService.read(ID_0)).usingRecursiveComparison().isEqualTo(CAR_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.carService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalCars() {
        Car extra = this.createCar();
        List<Car> cars = this.carService.findAll();

        assertThat(cars).extracting(Car::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, extra.getId());

        assertThat(this.carService.findAll()).extracting(Car::getId)
                .containsExactlyElementsOf(cars.stream().map(Car::getId).toList());
    }

    @Test
    void testCreate() {
        Car car = this.createCar();
        Car stored = this.carService.read(car.getId());

        assertThat(stored).usingRecursiveComparison().isEqualTo(car);
        assertThat(stored.getNumberOfSeats()).isEqualTo(5);
    }

    @Test
    void testCreateDuplicateLicensePlate() {
        Car car = Car.builder()
                .brand("Brand")
                .model("Model")
                .licensePlate(CAR_0.getLicensePlate())
                .registrationDate(LocalDate.now())
                .build();

        assertThatThrownBy(() -> this.carService.create(car))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(CAR_0.getLicensePlate());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Car original = this.createCar();
        Car replacement = Car.builder()
                .brand("UpdatedBrand")
                .model("UpdatedModel")
                .licensePlate("UP-" + UUID.randomUUID().toString().substring(0, 5))
                .numberOfSeats(2)
                .fuelType(FuelType.ELECTRIC)
                .build();

        this.carService.update(original.getId(), replacement);
        Car updated = this.carService.read(original.getId());

        assertThat(updated.getBrand()).isEqualTo(replacement.getBrand());
        assertThat(updated.getModel()).isEqualTo(replacement.getModel());
        assertThat(updated.getLicensePlate()).isEqualTo(replacement.getLicensePlate());
        assertThat(updated.getNumberOfSeats()).isEqualTo(2);
        assertThat(updated.getFuelType()).isEqualTo(FuelType.ELECTRIC);
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateSameLicensePlate() {
        Car car = this.createCar();
        car.setBrand("Updated Brand");
        this.carService.update(car.getId(), car);

        assertThat(this.carService.read(car.getId()).getBrand()).isEqualTo("Updated Brand");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.carService.update(id, CAR_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateLicensePlateLeavesCarUnchanged() {
        Car car = this.createCar();
        Car duplicate = Car.builder()
                .brand("Brand")
                .model("Model")
                .licensePlate(CAR_0.getLicensePlate())
                .numberOfSeats(4)
                .fuelType(FuelType.DIESEL)
                .build();

        assertThatThrownBy(() -> this.carService.update(car.getId(), duplicate))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(CAR_0.getLicensePlate());

        assertThat(this.carService.read(car.getId()).getLicensePlate()).isEqualTo(car.getLicensePlate());
    }

    @Test
    void testDelete() {
        Car car = this.createCar();
        this.carService.delete(car.getId());

        assertThatThrownBy(() -> this.carService.read(car.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingCar() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.carService.delete(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedCar() {
        Car car = this.createCar();

        ReservationEntity reservation = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDate.of(2025, 1, 1))
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(10, 0))
                .car(CarEntity.builder().id(car.getId()).build())
                .userId(UUID.randomUUID())
                .build();

        this.reservationRepository.saveAndFlush(reservation);

        assertThatThrownBy(() -> this.carService.delete(car.getId()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(car.getId().toString());

        assertThat(this.carService.read(car.getId()).getId()).isEqualTo(car.getId());
    }

    @Test
    void testUpdateSeatsAndFuel() {
        Car car = this.createCar();
        CarSeatsAndFuelUpdate update = new CarSeatsAndFuelUpdate(7, FuelType.ELECTRIC);

        Car updatedCar = this.carService.updateSeatsAndFuel(car.getId(), update);

        assertThat(updatedCar.getNumberOfSeats()).isEqualTo(7);
        assertThat(updatedCar.getFuelType()).isEqualTo(FuelType.ELECTRIC);
        // Verifica que la marca original no se alteró
        assertThat(updatedCar.getBrand()).isEqualTo(car.getBrand());
    }

    @Test
    void testUpdateSeatsAndFuelNotFound() {
        UUID missingId = UUID.randomUUID();
        CarSeatsAndFuelUpdate update = new CarSeatsAndFuelUpdate(2, FuelType.HYBRID);

        assertThatThrownBy(() -> this.carService.updateSeatsAndFuel(missingId, update))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString());
    }

    private Car createCar() {
        Car car = Car.builder()
                .brand("IT Brand " + UUID.randomUUID().toString().substring(0, 5))
                .model("IT Model")
                .licensePlate("LP-" + UUID.randomUUID().toString().substring(0, 5))
                .registrationDate(LocalDate.now())
                .build();
        car.doDefault();
        return this.carService.create(car);
    }
}