package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.ports.out.copyright.ClaimGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public List<Claim> findAll() {
        return this.claimRepository.findAllByOrderByNumberAsc().stream()
                .map(ClaimEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsByNumber(String number) {
        return this.claimRepository.existsByNumber(number);
    }

    @Override
    public Optional<Claim> read(UUID id) {
        return this.claimRepository.findById(id)
                .map(ClaimEntity::toDomain);
    }

    @Override
    public Claim update(Claim claim) {
        ClaimEntity claimEntity = this.claimRepository.findById(claim.getId())
                .orElseThrow(() -> new NotFoundException("Claim entity not found: " + claim.getId()));
        BeanUtils.copyProperties(claim, claimEntity, "defendant");
        claimEntity.setUserId(claim.getDefendant().getId());
        return this.claimRepository.save(claimEntity).toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.claimRepository.deleteById(id);
    }
}
