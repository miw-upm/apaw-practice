package es.upm.miw.apaw.domain.services.credentials;

import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationGateway verificationGateway;

    public Verification create(Verification verification) {
        verification.doDefault();
        return this.verificationGateway.create(verification);
    }
}