package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "power_of_attorney")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private PowerOfAttorneyTypeEntity type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerOfAttorneyStatusEntity status;
}
