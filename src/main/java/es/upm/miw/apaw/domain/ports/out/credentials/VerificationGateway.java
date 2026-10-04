package es.upm.miw.apaw.domain.ports.out.credentials;

import es.upm.miw.apaw.domain.model.credentials.Verification;

import java.util.Optional;
import java.util.UUID;

public interface VerificationGateway {

    Verification create(Verification verification);

    Optional<Verification> read(UUID id);

    Verification update(Verification verification);
}