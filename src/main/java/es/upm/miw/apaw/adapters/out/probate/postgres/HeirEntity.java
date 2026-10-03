package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.model.probate.HeirStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HeirEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String nationalId;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private BigDecimal sharePercentage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HeirStatus heirStatus;

    private String contactEmail;

    public HeirEntity(Heir heir) {
        BeanUtils.copyProperties(heir, this);
    }

    public Heir toDomain() {
        Heir heir = new Heir();
        BeanUtils.copyProperties(this, heir);
        return heir;
    }
}
