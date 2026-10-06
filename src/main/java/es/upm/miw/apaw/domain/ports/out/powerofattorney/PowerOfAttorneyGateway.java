package es.upm.miw.apaw.domain.ports.out.powerofattorney;

import java.util.UUID;

public interface PowerOfAttorneyGateway {

    boolean isReferenced(UUID partyId);

}
