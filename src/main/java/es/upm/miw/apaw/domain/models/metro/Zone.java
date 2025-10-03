package es.upm.miw.apaw.domain.models.metro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @NotBlank
    private String zoneType;

    @NotNull
    private BigDecimal ticketPrice;

    // Relationships
    private List<TrainStation> trainStations; // Zone has 0..* TrainStations
}
