package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyService {

    private final PowerOfAttorneyGateway powerOfAttorneyGateway;
    private final PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;
    private final UserFinder userFinder;

    public PowerOfAttorney create(CreationPowerOfAttorney creation) {
        this.assertProtocolNumberAvailable(creation.getProtocolNumber());
        PowerOfAttorneyParty principal = this.readParty(creation.getPrincipalId());
        PowerOfAttorneyParty attorney = this.readParty(creation.getAttorneyId());
        Map<UUID, UserSnapshot> users = this.findUsers(principal, attorney);
        principal.setUserSnapshot(this.findUser(users, principal));
        attorney.setUserSnapshot(this.findUser(users, attorney));
        PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
        BeanUtils.copyProperties(creation, powerOfAttorney, "principalId", "attorneyId");
        powerOfAttorney.setPrincipal(principal);
        powerOfAttorney.setAttorney(attorney);
        powerOfAttorney.doDefault();
        return this.powerOfAttorneyGateway.create(powerOfAttorney);
    }

    private void assertProtocolNumberAvailable(String protocolNumber) {
        if (this.powerOfAttorneyGateway.existsByProtocolNumber(protocolNumber)) {
            throw new ConflictException("Power of attorney protocol number already exists: " + protocolNumber);
        }
    }

    private PowerOfAttorneyParty readParty(UUID id) {
        return this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Power of attorney party id not found: " + id));
    }

    private Map<UUID, UserSnapshot> findUsers(
            PowerOfAttorneyParty principal, PowerOfAttorneyParty attorney) {
        Set<UUID> userIds = List.of(principal, attorney).stream()
                .map(party -> party.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
    }

    private UserSnapshot findUser(Map<UUID, UserSnapshot> users, PowerOfAttorneyParty party) {
        UUID userId = party.getUserSnapshot().getId();
        UserSnapshot user = users.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        return user;
    }
}
