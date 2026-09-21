package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.model.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.model.legalprocedure.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalTaskEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private LocalDateTime creatingDate;

    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

    public LegalTaskEntity(LegalTask legalTask) {
        BeanUtils.copyProperties(legalTask, this);
    }

    public LegalTask toDomain() {
        LegalTask legalTask = new LegalTask();
        BeanUtils.copyProperties(this, legalTask);
        return legalTask;
    }
}
