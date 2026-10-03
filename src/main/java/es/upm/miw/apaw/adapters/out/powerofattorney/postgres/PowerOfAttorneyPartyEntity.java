package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PowerOfAttorneyPartyEntity {

    @Id
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
