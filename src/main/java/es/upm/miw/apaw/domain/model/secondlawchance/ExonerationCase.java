package es.upm.miw.apaw.domain.model.secondlawchance;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ExonerationCase {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String caseNumber;

    private LocalDate filingDate;

    private LocalDate resolutionDate;

    @NotNull
    private BigDecimal totalAmount;

    private String lawyer;

    private List<Debt> debts;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.filingDate = LocalDate.now();
    }
}
