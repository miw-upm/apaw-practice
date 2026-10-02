package es.upm.miw.apaw.domain.ports.out.copyright;

import es.upm.miw.apaw.domain.model.copyright.Claim;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClaimGateway {
    Claim create(Claim claim, UUID creativeWorkId);

    boolean existsByNumber(String number);

    Optional<Claim> read(UUID id);

    Claim update(Claim claim);

    void delete(UUID id);

    List<Claim> findAll();
}
