package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PowerOfAttorneyAdapter implements PowerOfAttorneyGateway {

    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Override
    public boolean isReferenced(UUID partyId) {
        return this.powerOfAttorneyRepository.existsByPrincipal_Id(partyId)
                || this.powerOfAttorneyRepository.existsByAttorney_Id(partyId);
    }
}
