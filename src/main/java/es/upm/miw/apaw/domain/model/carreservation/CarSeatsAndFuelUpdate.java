package es.upm.miw.apaw.domain.model.carreservation;

import jakarta.validation.constraints.Positive;

public record CarSeatsAndFuelUpdate(
        @Positive Integer numberOfSeats,
        FuelType fuelType
) {
}