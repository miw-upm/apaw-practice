package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VerificationAdapter implements VerificationGateway {

    private final VerificationRepository verificationRepository;
}