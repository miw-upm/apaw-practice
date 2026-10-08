package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerOfAttorneyService {

    private final PowerOfAttorneyGateway powerOfAttorneyGateway;
    private final PowerOfAttorneyPartyGateway powerOfAttorneyPartyGateway;

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

    private PowerOfAttorneyParty readParty(UUID id) {
        return this.powerOfAttorneyPartyGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Power of attorney party id not found: " + id));
    }
}
