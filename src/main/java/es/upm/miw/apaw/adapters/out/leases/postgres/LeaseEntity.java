package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LeaseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String leaseNumber;

    @Column(unique = true)
    private String cadastralReference;

    @Column(nullable = false)
    private String propertyAddress;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal monthlyRent;

    private BigDecimal deposit;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaseType leaseType;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lease_id")
    private List<AmendmentEntity> amendments;

    @Column(nullable = false)
    private UUID userId;

    public LeaseEntity(Lease lease) {
        BeanUtils.copyProperties(lease, this, "amendments", "userSnapshot");
        this.amendments = lease.getAmendments().stream()
                .map(AmendmentEntity::new)
                .toList();
        this.userId = lease.getUserSnapshot().getId();
    }

    public Lease toDomain() {
        Lease lease = new Lease();
        BeanUtils.copyProperties(this, lease, "amendments", "userId");
        lease.setAmendments(new ArrayList<>(this.amendments.stream()
                .map(AmendmentEntity::toDomain)
                .toList()));
        lease.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return lease;
    }
}
