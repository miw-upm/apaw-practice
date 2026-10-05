package es.upm.miw.apaw.domain.model.carreservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CarSeatsAndFuelUpdate(
        @NotNull @Positive Integer numberOfSeats,
        @NotNull FuelType fuelType
) {
}