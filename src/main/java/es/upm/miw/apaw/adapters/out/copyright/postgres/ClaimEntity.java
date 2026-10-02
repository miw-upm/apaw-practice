package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClaimEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private LocalDateTime filingDate;

    @Column(nullable = false)
    private BigDecimal requestedCompensation;

    @Column(nullable = false)
    private Boolean urgent;

    private String resolutionNotes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CreativeWorkEntity creativeWork;

    private UUID userId;

    public ClaimEntity(Claim claim) {
        BeanUtils.copyProperties(claim, this, "userSnapshot");
        if (claim.getUserSnapshot() != null) {
            this.userId = claim.getUserSnapshot().getId();
        }
    }

    public Claim toDomain() {
        Claim claim = new Claim();
        BeanUtils.copyProperties(this, claim, "creativeWork", "userId");
        if (this.userId != null) {
            claim.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        }
        return claim;
    }
}

