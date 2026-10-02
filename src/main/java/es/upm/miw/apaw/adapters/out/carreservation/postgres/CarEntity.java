package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.FuelType;

import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private LocalDate registrationDate;

    private Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    public CarEntity(Car car) {
        BeanUtils.copyProperties(car, this);
    }

    public Car toDomain() {
        Car car = new Car();
        BeanUtils.copyProperties(this, car);
        return car;
    }
}
