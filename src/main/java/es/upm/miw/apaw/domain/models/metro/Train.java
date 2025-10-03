package es.upm.miw.apaw.domain.models.metro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {

    @NotBlank
    private String trainId;

    @NotNull
    private Integer trainVagonsNum;

    @NotNull
    private Boolean operational;

    @NotNull
    private Double maxSpeed;

    // Relationships
    private TrainLine trainLine;       // belongs to 1 TrainLine
    private TrainStation trainStation; // belongs to 0..1 TrainStation
}
