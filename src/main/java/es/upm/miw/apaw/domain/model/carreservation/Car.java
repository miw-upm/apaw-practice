package es.upm.miw.apaw.domain.model.carreservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotBlank
    private String licensePlate;

    @NotNull
    private LocalDate registrationDate;

    private Integer numberOfSeats;

    private FuelType fuelType;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.numberOfSeats == null) {
            this.numberOfSeats = 5;
        }
    }
}
