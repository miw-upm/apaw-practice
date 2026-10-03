package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "power_of_attorney_party")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorneyPartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Boolean fullMentalCapacity;

    private String companyName;

    @Column(nullable = false)
    private Boolean representationCompany;

    @Column(nullable = false)
    private UUID userId;
}
