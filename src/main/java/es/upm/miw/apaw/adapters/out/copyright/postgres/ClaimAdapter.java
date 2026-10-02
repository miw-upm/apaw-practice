package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClaimAdapter implements ClaimGateway {
    private final ClaimRepository claimRepository;

    @Override
    public Claim create(Claim claim) {
        return this.claimRepository
                .save(new ClaimEntity(claim))
                .toDomain();
    }

    @Override
    public boolean existsByNumber(String number) {
        return this.claimRepository.existsByNumber(number);
    }
}
