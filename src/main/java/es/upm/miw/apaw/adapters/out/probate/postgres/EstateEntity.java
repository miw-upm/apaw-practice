package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.probate.Estate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class EstateEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String fileNumber;

    @Column(nullable = false)
    private LocalDate openedDate;

    @Column(nullable = false)
    private String deceasedName;

    @Column(nullable = false)
    private BigDecimal netValue;

    @Column(nullable = false)
    private Boolean lastWill;

    private LocalDate closingDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private List<HeirEntity> heirs;

    @Column(nullable = false)
    private UUID userId;

    public EstateEntity(Estate estate) {
        BeanUtils.copyProperties(estate, this, "heirs", "userSnapshot");
        this.heirs = estate.getHeirs().stream()
                .map(HeirEntity::new)
                .toList();
        this.userId = estate.getUserSnapshot().getId();
    }

    public Estate toDomain() {
        Estate estate = new Estate();
        BeanUtils.copyProperties(this, estate, "heirs", "userId");
        estate.setHeirs(new ArrayList<>(this.heirs.stream()
                .map(HeirEntity::toDomain)
                .toList()));
        estate.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return estate;
    }
}
