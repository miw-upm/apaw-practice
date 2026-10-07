package es.upm.miw.apaw.domain.model.secondlawchance;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    private String lawyer;

    private List<Debt> debts;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.filingDate = LocalDate.now();
    }

    public ExonerationCase ofSummary() {
        return ExonerationCase.builder()
                .id(this.id)
                .caseNumber(this.caseNumber)
                .filingDate(this.filingDate)
                .resolutionDate(this.resolutionDate)
                .lawyer(this.lawyer)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}
