package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.ports.out.carreservation.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarAdapter implements CarGateway {
    private final CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return this.carRepository
                .save(new CarEntity(car))
                .toDomain();
    }

    @Override
    public boolean existsByLicensePlate(String licensePlate) {
        return this.carRepository.existsByLicensePlate(licensePlate);
    }

    @Override
    public Optional<Car> read(UUID id) {
        return this.carRepository.findById(id)
                .map(CarEntity::toDomain);
    }
}