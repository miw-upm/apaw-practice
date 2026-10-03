package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class LegalIssueEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private Boolean resolved;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    public LegalIssueEntity(LegalIssue legalIssue) {
        BeanUtils.copyProperties(legalIssue, this);
    }

    public LegalIssue toDomain() {
        LegalIssue legalIssue = new LegalIssue();
        BeanUtils.copyProperties(this, legalIssue);
        return legalIssue;
    }
}
