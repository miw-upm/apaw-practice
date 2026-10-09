package es.upm.miw.apaw.domain.ports.out.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyFindCriteria;

import java.util.List;
import java.util.UUID;

public interface PowerOfAttorneyGateway {

    PowerOfAttorney create(PowerOfAttorney powerOfAttorney);

    List<PowerOfAttorney> find(PowerOfAttorneyFindCriteria criteria);

    boolean existsByProtocolNumber(String protocolNumber);

    boolean isReferenced(UUID partyId);

}
