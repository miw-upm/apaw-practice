package es.upm.miw.apaw.domain.ports.out.credentials;

import es.upm.miw.apaw.domain.model.credentials.Verification;

public interface VerificationGateway {

    Verification create(Verification verification);
}