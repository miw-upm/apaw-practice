package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.ports.out.leases.AmendmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AmendmentAdapter implements AmendmentGateway {
    private final AmendmentRepository amendmentRepository;
    private final LeaseRepository leaseRepository;

    @Override
    public Amendment create(Amendment amendment) {
        return this.amendmentRepository
                .save(new AmendmentEntity(amendment))
                .toDomain();
    }

    @Override
    public List<Amendment> findAll() {
        return this.amendmentRepository.findAllByOrderByEffectiveDateAscAmendmentNumberAscIdAsc().stream()
                .map(AmendmentEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Amendment> read(UUID id) {
        return this.amendmentRepository.findById(id)
                .map(AmendmentEntity::toDomain);
    }

    @Override
    public Amendment update(Amendment amendment) {
        return this.amendmentRepository
                .save(new AmendmentEntity(amendment))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.amendmentRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.leaseRepository.existsByAmendmentsId(id);
    }
}
