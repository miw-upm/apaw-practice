package es.upm.miw.apaw.domain.ports.out.copyright;

import es.upm.miw.apaw.domain.model.copyright.Claim;

import java.util.UUID;

public interface ClaimGateway {
    Claim create(Claim claim, UUID creativeWorkId);

    boolean existsByNumber(String number);
}
