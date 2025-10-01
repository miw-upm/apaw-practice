package es.upm.miw.apaw.domain.models.winery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class TastingSession {
    @NotNull
    @NotBlank
    private Long idSession;
    private LocalDate date;
    private Integer capacity;
    private String location;
    private List<Wine> wines;
    @NotNull
    @NotEmpty
    private List<Evaluation> evaluations;
}
