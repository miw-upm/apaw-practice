package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import jakarta.persistence.*;
import lombok.*;
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
}
