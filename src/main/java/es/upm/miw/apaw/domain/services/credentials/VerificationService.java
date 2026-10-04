package es.upm.miw.apaw.domain.services.credentials;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationGateway verificationGateway;

    public Verification create(Verification verification) {
        verification.doDefault();
        return this.verificationGateway.create(verification);
    }

    public Verification read(UUID id) {
        return this.verificationGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("Verification id not found: " + id));
    }

    public Verification update(UUID id, Verification verification) {
        Verification storedVerification = this.read(id);

        storedVerification.setVerifiedAt(verification.getVerifiedAt());
        storedVerification.setMethod(verification.getMethod());
        storedVerification.setName(verification.getName());
        storedVerification.setNotes(verification.getNotes());
        storedVerification.setScore(verification.getScore());
        storedVerification.setVerificationStatus(verification.getVerificationStatus());

        return this.verificationGateway.update(storedVerification);
    }

    public void delete(UUID id) {
        if (this.verificationGateway.isAssociatedWithCredential(id)) {
            throw new ConflictException(
                    "Verification is associated with a credential: " + id);
        }

        this.verificationGateway.delete(id);
    }

    public List<Verification> findAll() {
        return this.verificationGateway.findAll();
    }
}