package es.upm.miw.apaw.domain.models.winery;

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
public class Evaluation {
    @NotNull
    @NotBlank
    private Long idEvaluation;
    private Integer score;
    private String comment;
}
