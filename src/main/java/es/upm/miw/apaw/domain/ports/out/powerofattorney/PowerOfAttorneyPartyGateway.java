package es.upm.miw.apaw.domain.ports.out.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;

import java.util.Optional;
import java.util.UUID;

public interface PowerOfAttorneyPartyGateway {

    PowerOfAttorneyParty create(PowerOfAttorneyParty party);

    Optional<PowerOfAttorneyParty> read(UUID id);

    PowerOfAttorneyParty update(PowerOfAttorneyParty party);

    void delete(UUID id);

}
