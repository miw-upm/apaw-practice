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
    private Integer lineNumber;

    @NotBlank
    private String lineColor;

    @NotNull
    private Integer stationsNum;

    @NotNull
    private Boolean circular;

    // Relationships
    private List<Train> trains;              // TrainLine has 0..* Trains
    private List<TrainStation> trainStations; // TrainLine belongs to 0..* TrainStations
}
