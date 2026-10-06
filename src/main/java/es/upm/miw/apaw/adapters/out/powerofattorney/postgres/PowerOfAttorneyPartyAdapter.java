package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<PowerOfAttorneyParty> read(UUID id) {
        return this.powerOfAttorneyPartyRepository.findById(id)
                .map(PowerOfAttorneyPartyEntity::toDomain);
    }

    @Override
    public PowerOfAttorneyParty update(PowerOfAttorneyParty party) {
        return this.powerOfAttorneyPartyRepository
                .save(new PowerOfAttorneyPartyEntity(party))
                .toDomain();
    }
}
