package es.upm.miw.apaw.domain.models.vehicle;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @NotNull
    @NotBlank
    private String plate;
    @NotNull
    @NotBlank
    private String brand;
    @NotNull
    @NotBlank
    private String model;
    private LocalDate registrationDate;
    private Engine engine;
    @NotNull
    private List<Documentation> documentations;
    private List<Extra> extras;
    @NotNull
    private UserDto owner;
}
