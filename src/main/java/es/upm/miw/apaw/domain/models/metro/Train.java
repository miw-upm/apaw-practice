package es.upm.miw.apaw.domain.models.metro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {

    @NotBlank
    private UUID id;

    @NotNull
    private Integer numCars;

    @NotNull
    private Boolean operational;

    @NotNull
    private Double maxSpeed;
}
