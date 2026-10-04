package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VerificationAdapter implements VerificationGateway {

    private final VerificationRepository verificationRepository;

    @Override
    public Verification create(Verification verification) {
        return this.verificationRepository
                .save(new VerificationEntity(verification))
                .toDomain();
    }

    @Override
    public Optional<Verification> read(UUID id) {
        return this.verificationRepository.findById(id)
                .map(VerificationEntity::toDomain);
    }

    @Override
    public Verification update(Verification verification) {
        return this.verificationRepository
                .save(new VerificationEntity(verification))
                .toDomain();
    }
}