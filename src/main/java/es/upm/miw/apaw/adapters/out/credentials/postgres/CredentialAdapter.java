package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.ports.out.credentials.CredentialGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CredentialAdapter implements CredentialGateway {

    private final CredentialRepository credentialRepository;
}