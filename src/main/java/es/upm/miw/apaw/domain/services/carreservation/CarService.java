package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.CarSeatsAndFuelUpdate;
import es.upm.miw.apaw.domain.ports.out.carreservation.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarGateway carGateway;

    public Car create(Car car) {

        if (this.carGateway.existsByLicensePlate(car.getLicensePlate())) {
            throw new ConflictException(
                    "License plate already exists: " + car.getLicensePlate()
            );
        }

        return this.carGateway.create(car);
    }

    public Car read(UUID id) {
        return this.carGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("Car id not found: " + id));
    }

    public Car update(UUID id, Car car) {

        Car storedCar = this.read(id);

        if (!storedCar.getLicensePlate().equals(car.getLicensePlate())
                && this.carGateway.existsByLicensePlate(car.getLicensePlate())) {

            throw new ConflictException(
                    "License plate already exists: " + car.getLicensePlate()
            );
        }

        storedCar.setBrand(car.getBrand());
        storedCar.setModel(car.getModel());
        storedCar.setLicensePlate(car.getLicensePlate());
        storedCar.setNumberOfSeats(car.getNumberOfSeats());
        storedCar.setFuelType(car.getFuelType());

        return this.carGateway.update(storedCar);
    }

    public void delete(UUID id) {

        this.read(id);

        if (this.carGateway.isUsedByReservation(id)) {
            throw new ConflictException(
                    "Car is used by a reservation: " + id
            );
        }

        this.carGateway.delete(id);
    }

    public List<Car> findAll() {
        return this.carGateway.findAll();
    }

    public Car updateSeatsAndFuel(UUID id, CarSeatsAndFuelUpdate carUpdate) {
            Car car = this.read(id);

            if (carUpdate.numberOfSeats() != null) {
                car.setNumberOfSeats(carUpdate.numberOfSeats());
            }
            if (carUpdate.fuelType() != null) {
                car.setFuelType(carUpdate.fuelType());
            }

            return this.carGateway.update(car);
    }
}