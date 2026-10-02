package es.upm.miw.apaw.domain.model.probate;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Heir {

    @EqualsAndHashCode.Include
    private UUID id;

    private String fullName;

    private String nationalId;

    private LocalDate birthDate;

    private BigDecimal sharePercentage;

    private HeirStatus status;

    private String contactEmail;
}
