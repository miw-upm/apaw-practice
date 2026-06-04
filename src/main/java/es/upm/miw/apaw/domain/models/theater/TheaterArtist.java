package es.upm.miw.apaw.domain.models.theater;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterArtist {
    @NotNull
    @NotBlank
    private String artistCode;
    @NotNull
    @NotBlank
    private String artistFullName;
    @NotNull
    private LocalDate artistBirthDate;
    @NotNull
    private BigDecimal artistFee;
    @NotNull
    private Boolean artistActive;
    @NotNull
    private Set<TheaterPerformance> artistPerformances;
}
