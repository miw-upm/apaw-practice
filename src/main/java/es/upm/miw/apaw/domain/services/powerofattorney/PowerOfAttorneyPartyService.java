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

import java.util.Map;
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
        PowerOfAttorneyParty party = this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Power of attorney party id not found: " + id));
        return this.enrichUserSnapshot(party);
    }

    private PowerOfAttorneyParty enrichUserSnapshot(PowerOfAttorneyParty party) {
        return this.enrichUserSnapshot(party, Map.of(party.getUserSnapshot().getId(), this.readUser(party.getUserSnapshot().getId())));
    }

    private PowerOfAttorneyParty enrichUserSnapshot(
            PowerOfAttorneyParty party, Map<UUID, UserSnapshot> usersById) {
        UUID userId = party.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        party.setUserSnapshot(user);
        return party;
    }
}
