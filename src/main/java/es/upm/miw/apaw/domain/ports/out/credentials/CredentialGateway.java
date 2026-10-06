package es.upm.miw.apaw.domain.ports.out.credentials;

import es.upm.miw.apaw.domain.model.credentials.Credential;

public interface CredentialGateway {

    Credential create(Credential credential);

    boolean existsByNumber(String number);

    boolean existsByRegistryCode(String registryCode);
}