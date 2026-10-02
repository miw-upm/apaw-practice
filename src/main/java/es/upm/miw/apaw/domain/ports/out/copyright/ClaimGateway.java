package es.upm.miw.apaw.domain.ports.out.copyright;

import es.upm.miw.apaw.domain.model.copyright.Claim;

public interface ClaimGateway {
    Claim create(Claim claim);

    boolean existsByNumber(String number);
}
