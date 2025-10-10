package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Location {

    @NotNull
    private Integer currentStock;

    @NotBlank
    private String position;

    private LocalDateTime lastUpdateDate;

    @NotNull
    private Boolean availability;

}
