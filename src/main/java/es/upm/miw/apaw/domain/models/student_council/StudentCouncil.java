package es.upm.miw.apaw.domain.models.student_council;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCouncil {

    @NotNull
    private UUID councilId;

    @NotNull
    private String councilName;

    @NotNull
    private String councilLocation;

    @NotNull
    private BigDecimal councilBudget;

    private List<Representative> representatives;
}