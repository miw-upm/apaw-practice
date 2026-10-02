package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClaimAdapter implements ClaimGateway {
    private final ClaimRepository claimRepository;
    private final CreativeWorkRepository creativeWorkRepository;

    @Override
    public Claim create(Claim claim, UUID creativeWorkId) {
        ClaimEntity claimEntity = new ClaimEntity(claim);
        claimEntity.setCreativeWork(this.creativeWorkRepository.getReferenceById(creativeWorkId));
        return this.claimRepository.save(claimEntity).toDomain();
    }

    @Override
    public boolean existsByNumber(String number) {
        return this.claimRepository.existsByNumber(number);
    }
}
