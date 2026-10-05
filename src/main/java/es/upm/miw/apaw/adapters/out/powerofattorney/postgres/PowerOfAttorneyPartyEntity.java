package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    public PowerOfAttorneyPartyEntity(PowerOfAttorneyParty party) {
        BeanUtils.copyProperties(party, this, "userSnapshot");
        this.userId = party.getUserSnapshot().getId();
    }

    public PowerOfAttorneyParty toDomain() {
        PowerOfAttorneyParty party = new PowerOfAttorneyParty();
        BeanUtils.copyProperties(this, party, "userId");
        party.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return party;
    }
}
