package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyPartyService {

    private final PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;
    private final UserFinder userFinder;

    public PowerOfAttorneyParty create(PowerOfAttorneyParty party) {
        party.setUserSnapshot(this.readUser(party.getUserSnapshot().getId()));
        party.doDefault();
        return this.powerOfAttorneyPartyGateway.create(party);
    }

    private UserSnapshot readUser(UUID id) {
        return this.userFinder.read(id);
    }
}
