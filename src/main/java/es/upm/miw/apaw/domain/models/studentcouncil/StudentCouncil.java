package es.upm.miw.apaw.domain.models.studentcouncil;

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
    private UUID id;

    @NotNull
    private String council;

    @NotNull
    private String site;

    @NotNull
    private BigDecimal resources;

    private List<Representative> representatives;
}