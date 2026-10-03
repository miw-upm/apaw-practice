package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyPartyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PowerOfAttorneyPartyAdapter implements PowerOfAttorneyPartyGateway {

    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;
}
