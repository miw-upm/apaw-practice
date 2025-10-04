package es.upm.miw.apaw.domain.models.metro;

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
public class TrainStation {

    @NotBlank
    private String name;

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
    private Zone zone;                   // belongs to one Zone
    private List<UserDto> users;
}
