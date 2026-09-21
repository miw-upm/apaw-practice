package es.upm.miw.apaw.domain.model.legalprocedure;

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
public class LegalProcedure {

    @EqualsAndHashCode.Include
    private UUID id;

    private String title;

    private LocalDate startedDate;

    private LocalDate closingDate;

    private BigDecimal budget;

    private String budgetProposal;

    private Boolean vatIncluded;

    private List< LegalTask> legalTasks;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.startedDate = LocalDate.now();
        if (this.vatIncluded == null) {
            this.vatIncluded = false;
        }
    }

    public boolean isOpened() {
        return this.closingDate == null;
    }

    public LegalProcedure ofSummary() {
        return LegalProcedure.builder()
                .id(this.id)
                .title(this.title)
                .startedDate(this.startedDate)
                .closingDate(this.closingDate)
                .budget(this.budget)
                .vatIncluded(this.vatIncluded)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}
