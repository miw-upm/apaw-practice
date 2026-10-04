package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StuckTaskRuleEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String procedureKeyword;

    @Column(nullable = false)
    private Integer thresholdDays;

    private BigDecimal penaltyAmount;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private UUID createdByUserId;   // plano

    public StuckTaskRule toDomain() {
        StuckTaskRule stuckTaskRule = new StuckTaskRule();
        BeanUtils.copyProperties(this, stuckTaskRule, "createdByUserId");
        stuckTaskRule.setCreatedByUser(UserSnapshot.builder().id(this.createdByUserId).build());
        return stuckTaskRule;
    }
}
