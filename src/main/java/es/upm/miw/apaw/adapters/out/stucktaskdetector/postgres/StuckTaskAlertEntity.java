package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public StuckTaskAlertEntity(StuckTaskAlert stuckTaskAlert) {
        BeanUtils.copyProperties(stuckTaskAlert, this, "stuckTaskRule");
    }

    public StuckTaskAlert toDomain() {
        StuckTaskAlert stuckTaskAlert = new StuckTaskAlert();
        BeanUtils.copyProperties(this, stuckTaskAlert, "stuckTaskRule");
        stuckTaskAlert.setStuckTaskRule(this.stuckTaskRule.toDomain());
        return stuckTaskAlert;
    }
}