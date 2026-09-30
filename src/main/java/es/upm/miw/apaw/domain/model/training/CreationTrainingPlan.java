package es.upm.miw.apaw.domain.model.training;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationTrainingPlan {

    @NotBlank
    private String planCode;

    private LocalDate endDate;

    private BigDecimal evaluationScore;

    @NotEmpty
    private List<UUID> courseIds;

    @NotEmpty
    private List<UUID> userIds;
}
