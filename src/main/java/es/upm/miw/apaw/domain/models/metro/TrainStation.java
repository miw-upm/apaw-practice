package es.upm.miw.apaw.domain.models.metro;

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
public class TrainStation {

    @NotBlank
    private String trainName;

    @NotNull
    private Integer capacity;

    @NotBlank
    private String location;

    @NotNull
    private Boolean multipleLines;

    @NotNull
    private LocalDate inaugurationDate;

    // Relationships
    private List<TrainLine> trainLines;  // 0..* TrainLine belongs to TrainStation
    private List<Train> trains;          // 0..* Train currently has TrainStation
    private Zone zone;                   // belongs to one Zone
}
