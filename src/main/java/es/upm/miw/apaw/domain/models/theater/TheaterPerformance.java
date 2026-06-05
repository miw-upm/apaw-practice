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
public class TheaterPerformance {
    @NotNull
    @NotBlank
    private String performanceCode;
    @NotNull
    @NotBlank
    private String performanceTitle;
    @NotNull
    private LocalDate performanceDate;
    @NotNull
    private Integer performanceDurationMinutes;
    @NotNull
    private BigDecimal performanceTicketPrice;
    @NotNull
    private TheaterHall performanceHall;
    @NotNull
    private Set<TheaterArtist> performanceArtists;
}
