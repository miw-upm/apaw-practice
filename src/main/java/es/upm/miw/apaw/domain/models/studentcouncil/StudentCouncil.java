package es.upm.miw.apaw.domain.models.studentcouncil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentCouncil {

    @NotNull
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    @NotBlank
    private String council;

    @NotNull
    @NotBlank
    private String site;

    @NotNull
    private BigDecimal resources;


    private List<Representative> representatives;
}