package es.upm.miw.apaw.domain.ports.out.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;

import java.util.UUID;

public interface PowerOfAttorneyGateway {

    PowerOfAttorney create(PowerOfAttorney powerOfAttorney);

    boolean existsByProtocolNumber(String protocolNumber);

    boolean isReferenced(UUID partyId);

}
