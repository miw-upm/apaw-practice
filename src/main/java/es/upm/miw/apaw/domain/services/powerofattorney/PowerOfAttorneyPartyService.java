package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyPartyService {

    private final PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;
    private final UserFinder userFinder;

    public PowerOfAttorneyParty create(CreationPowerOfAttorneyParty party) {
        PowerOfAttorneyParty powerOfAttorneyParty = new PowerOfAttorneyParty();
        BeanUtils.copyProperties(party, powerOfAttorneyParty);
        powerOfAttorneyParty.setUserSnapshot(this.readUser(party.userId()));
        powerOfAttorneyParty.doDefault();
        return this.powerOfAttorneyPartyGateway.create(powerOfAttorneyParty);
    }

    private UserSnapshot readUser(UUID id) {
        return this.userFinder.read(id);
    }

    public PowerOfAttorneyParty read(UUID id) {
        PowerOfAttorneyParty powerOfAttorneyParty = this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Power of attorney party id not found: " + id));
        UUID userId = powerOfAttorneyParty.getUserSnapshot().getId();
        powerOfAttorneyParty.setUserSnapshot(this.readUser(userId));
        return powerOfAttorneyParty;
    }
}
