package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.FormatType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreativeWorkEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String registrationCode;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal estimatedValuation;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private String authorPenName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatType formatType;

    @Column(nullable = false)
    private UUID userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "creative_work_id")
    private java.util.List<ClaimEntity> claims;

    public CreativeWorkEntity(CreativeWork creativeWork) {
        BeanUtils.copyProperties(creativeWork, this, "author", "claims");
        if (creativeWork.getAuthor() != null) {
            this.userId = creativeWork.getAuthor().getId();
        }
        if (creativeWork.getClaims() != null) {
            this.claims = creativeWork.getClaims().stream()
                    .map(ClaimEntity::new)
                    .collect(java.util.stream.Collectors.toList());
        } else {
            this.claims = new java.util.ArrayList<>();
        }
    }

    public CreativeWork toDomain() {
        CreativeWork creativeWork = new CreativeWork();
        BeanUtils.copyProperties(this, creativeWork, "userId", "claims");
        if (this.userId != null) {
            creativeWork.setAuthor(es.upm.miw.apaw.domain.model.UserSnapshot.builder().id(this.userId).build());
        }
        if (this.claims != null) {
            creativeWork.setClaims(this.claims.stream()
                    .map(ClaimEntity::toDomain)
                    .collect(java.util.stream.Collectors.toList()));
        } else {
            creativeWork.setClaims(new java.util.ArrayList<>());
        }
        return creativeWork;
    }
}
