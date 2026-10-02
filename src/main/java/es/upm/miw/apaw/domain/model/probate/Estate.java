package es.upm.miw.apaw.domain.model.probate;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estate {

    @EqualsAndHashCode.Include
    private UUID id;

    private String fileNumber;

    private LocalDate openedDate;

    private String deceasedName;

    private BigDecimal netValue;

    private Boolean lastWill;

    private LocalDate closingDate;

    private List<Heir> heirs;

    private UserSnapshot userSnapshot;
}
