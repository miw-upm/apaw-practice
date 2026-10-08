package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyStatus;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorneyEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String protocolNumber;

    @Column(nullable = false)
    private LocalDate grantDate;

    private LocalDate expirationDate;

    @Column(nullable = false)
    private String scope;

    private String limitations;

    @Column(nullable = false)
    private String notaryName;

    @Column(nullable = false)
    private String notaryOffice;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "principal_id", nullable = false)
    private PowerOfAttorneyPartyEntity principal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attorney_id", nullable = false)
    private PowerOfAttorneyPartyEntity attorney;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerOfAttorneyType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerOfAttorneyStatus status;

    public PowerOfAttorneyEntity(
            PowerOfAttorney powerOfAttorney,
            PowerOfAttorneyPartyEntity principal,
            PowerOfAttorneyPartyEntity attorney) {
        BeanUtils.copyProperties(powerOfAttorney, this, "principal", "attorney");
        this.principal = principal;
        this.attorney = attorney;
    }
}
