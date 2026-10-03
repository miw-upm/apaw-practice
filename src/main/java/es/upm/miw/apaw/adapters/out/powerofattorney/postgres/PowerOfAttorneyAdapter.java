package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PowerOfAttorneyAdapter implements PowerOfAttorneyGateway {

    private final PowerOfAttorneyRepository powerOfAttorneyRepository;
}
