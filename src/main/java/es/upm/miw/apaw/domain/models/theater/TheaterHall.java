package es.upm.miw.apaw.domain.models.theater;

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
public class TheaterHall {
    @NotNull
    @NotBlank
    private String hallCode;
    @NotNull
    @NotBlank
    private String hallName;
    @NotNull
    private Integer hallCapacity;
    @NotNull
    private Boolean hallAccessible;
}
