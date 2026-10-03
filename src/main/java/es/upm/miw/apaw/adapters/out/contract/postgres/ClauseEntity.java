package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClauseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClauseType type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate effectiveFrom;

    private LocalDate effectiveUntil;

    private String notes;

    @Column(nullable = false)
    private Integer version;

    public ClauseEntity(Clause clause) {
        BeanUtils.copyProperties(clause, this);
    }

    public Clause toDomain() {
        Clause clause = new Clause();
        BeanUtils.copyProperties(this, clause);
        return clause;
    }
}