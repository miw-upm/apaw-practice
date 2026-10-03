package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StuckTaskAlertEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true)
    private String reference;

    @Column(nullable = false)
    private LocalDate detectedAt;

    private LocalDate resolvedAt;

    @Column(nullable = false)
    private Boolean escalated;

    private String resolutionNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stuck_task_rule_id", nullable = false)
    private StuckTaskRuleEntity stuckTaskRule;
}