package es.upm.miw.apaw.domain.model.immigrationissues;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImmigrationIssue {

    @EqualsAndHashCode.Include
    private UUID id;

    private String subject;

    private String clientNationality;

    private String clientImmigrationStatus;

    private LocalDateTime openedAt;

    private LocalDate responseDueDate;

    private BigDecimal estimatedCost;

    private List<LawBasis> lawBases;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.openedAt = LocalDateTime.now();
        if (this.estimatedCost == null) {
            this.estimatedCost = BigDecimal.ZERO;
        }
    }

    public ImmigrationIssue ofSummary() {
        return ImmigrationIssue.builder()
                .id(this.id)
                .subject(this.subject)
                .clientNationality(this.clientNationality)
                .clientImmigrationStatus(this.clientImmigrationStatus)
                .openedAt(this.openedAt)
                .responseDueDate(this.responseDueDate)
                .estimatedCost(this.estimatedCost)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}