package es.upm.miw.apaw.domain.models.metro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainLine {
    @NotNull
    private Integer number;

    @NotBlank
    private String color;

    @NotNull
    private Integer numStations;

    @NotNull
    private Boolean circular;

    // Relationships
    private List<Train> trains;              // TrainLine has 0..* Trains
}
