package es.upm.miw.apaw.domain.models.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    @NotNull
    @NotBlank
    private String registrationNumber;
    @NotNull
    @NotBlank
    private String model;
    private Integer seatCount;
    private LocalDateTime createdAt;
    private String manufacturer;
}
