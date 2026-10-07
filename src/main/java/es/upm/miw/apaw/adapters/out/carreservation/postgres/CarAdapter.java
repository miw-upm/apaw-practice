package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.ports.out.carreservation.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarAdapter implements CarGateway {
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;

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

    @Override
    public Car update(Car car) {
        CarEntity carEntity = new CarEntity(car);

        return this.carRepository.save(carEntity)
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public boolean isUsedByReservation(UUID id) {
        return this.reservationRepository.existsByCarId(id);
    }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAllByOrderByLicensePlateAsc()
                .stream()
                .map(CarEntity::toDomain)
                .toList();
    }
}