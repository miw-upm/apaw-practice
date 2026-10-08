package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerOfAttorneyAdapter implements PowerOfAttorneyGateway {

    private final PowerOfAttorneyRepository powerOfAttorneyRepository;
    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @Override
    @Transactional
    public PowerOfAttorney create(PowerOfAttorney powerOfAttorney) {
        PowerOfAttorneyEntity entity = new PowerOfAttorneyEntity(
                powerOfAttorney,
                this.powerOfAttorneyPartyRepository.getReferenceById(powerOfAttorney.getPrincipal().getId()),
                this.powerOfAttorneyPartyRepository.getReferenceById(powerOfAttorney.getAttorney().getId()));
        this.powerOfAttorneyRepository.save(entity);
        return powerOfAttorney;
    }

    @Override
    public boolean existsByProtocolNumber(String protocolNumber) {
        return this.powerOfAttorneyRepository.existsByProtocolNumber(protocolNumber);
    }

    @Override
    public boolean isReferenced(UUID partyId) {
        return this.powerOfAttorneyRepository.existsByPrincipal_Id(partyId)
                || this.powerOfAttorneyRepository.existsByAttorney_Id(partyId);
    }
}
