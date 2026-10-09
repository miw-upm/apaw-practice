package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyFindCriteria;
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

        if (this.powerOfAttorneyGateway.existsByProtocolNumber(creation.getProtocolNumber())) {
            throw new ConflictException(
                    "Power of attorney protocol number already exists: " + creation.getProtocolNumber());
        }
        if (creation.getGrantDate().isAfter(creation.getExpirationDate())){
            throw new BadRequestException(
                    "Power of attorney grand date cannot be later than expiration date: " + creation.getProtocolNumber());
        }
        PowerOfAttorneyParty principal = this.readParty(creation.getPrincipalId());
        PowerOfAttorneyParty attorney = this.readParty(creation.getAttorneyId());
        if (principal.getUserSnapshot().getId().equals(attorney.getUserSnapshot().getId())) {
            throw new BadRequestException("Principal and attorney must be different users");
        }

        PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
        BeanUtils.copyProperties(creation, powerOfAttorney, "principalId", "attorneyId");
        powerOfAttorney.setPrincipal(principal);
        powerOfAttorney.setAttorney(attorney);
        powerOfAttorney.doDefault();
        return this.powerOfAttorneyGateway.create(powerOfAttorney);
    }

    public List<PowerOfAttorney> find(PowerOfAttorneyFindCriteria criteria) {
        PowerOfAttorneyFindCriteria filters = criteria == null ? new PowerOfAttorneyFindCriteria() : criteria;
        List<PowerOfAttorney> powers = this.powerOfAttorneyGateway.find(filters);
        if (powers.isEmpty() || (!filters.hasIdentity() && !filters.hasLegalPowerOfAttorney())) {
            return powers;
        }
        Set<UUID> userIds = powers.stream()
                .flatMap(power -> java.util.stream.Stream.of(
                        power.getPrincipal().getUserSnapshot().getId(),
                        power.getAttorney().getUserSnapshot().getId()))
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> users = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        powers.forEach(power -> {
            power.getPrincipal().setUserSnapshot(users.get(power.getPrincipal().getUserSnapshot().getId()));
            power.getAttorney().setUserSnapshot(users.get(power.getAttorney().getUserSnapshot().getId()));
        });
        return powers.stream()
                .filter(power -> !filters.hasIdentity() || this.matchesIdentity(power, filters.getIdentity()))
                .filter(power -> !filters.hasLegalPowerOfAttorney()
                        || this.isLegalPower(power) == filters.getLegalPowerOfAttorney())
                .toList();
    }

    private boolean matchesIdentity(PowerOfAttorney power, String identity) {
        return (power.getPrincipal().getUserSnapshot() != null
                && identity.equalsIgnoreCase(power.getPrincipal().getUserSnapshot().getIdentity()))
                || (power.getAttorney().getUserSnapshot() != null
                && identity.equalsIgnoreCase(power.getAttorney().getUserSnapshot().getIdentity()));
    }

    private boolean isLegalPower(PowerOfAttorney power) {
        return this.isLegalParty(power.getPrincipal()) && this.isLegalParty(power.getAttorney());
    }

    private boolean isLegalParty(PowerOfAttorneyParty party) {
        UserSnapshot user = party.getUserSnapshot();
        return party.getAge() != null && party.getAge() > 18
                && Boolean.TRUE.equals(party.getFullMentalCapacity())
                && user != null && user.getIdentity() != null && !user.getIdentity().isBlank();
    }

    private PowerOfAttorneyParty readParty(UUID id) {
        return this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Power of attorney party id not found: " + id));
    }
}
