package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtStatus;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JudicialCourtEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer number;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(length = 5)
    private String postalCode;

    private String phone;

    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private JudicialCourtTypeEntity type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JudicialCourtStatus status;

    @ElementCollection
    private List<UUID> lawyerIds;

    public JudicialCourtEntity(JudicialCourt judicialCourt) {
        BeanUtils.copyProperties(judicialCourt, this, "type", "lawyers", "status");
        if (judicialCourt.getType() != null) {
            this.type = new JudicialCourtTypeEntity(judicialCourt.getType());
        }
        if (judicialCourt.getLawyers() != null) {
            this.lawyerIds = judicialCourt.getLawyers().stream()
                    .map(UserSnapshot::getId)
                    .toList();
        }
        this.status = judicialCourt.getStatus() == null ? JudicialCourtStatus.ACTIVE : judicialCourt.getStatus();
    }

    public JudicialCourt toDomain() {
        JudicialCourt judicialCourt = new JudicialCourt();
        BeanUtils.copyProperties(this, judicialCourt, "type", "lawyerIds");
        judicialCourt.setType(this.type == null ? null : this.type.toDomain());
        judicialCourt.setLawyers(this.lawyerIds == null ? null : this.lawyerIds.stream()
                .map(id -> UserSnapshot.builder().id(id).build())
                .toList());
        return judicialCourt;
    }
}
