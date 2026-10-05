package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PowerOfAttorneyPartyAdapter implements PowerOfAttorneyPartyGateway {

    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @Override
    public PowerOfAttorneyParty create(PowerOfAttorneyParty party) {
        return this.powerOfAttorneyPartyRepository
                .save(new PowerOfAttorneyPartyEntity(party))
                .toDomain();
    }
}
