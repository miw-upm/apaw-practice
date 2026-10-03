package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CreativeWorkAdapter implements CreativeWorkGateway {
    private final CreativeWorkRepository creativeWorkRepository;

    @Override
    public boolean existsById(UUID id) {
        return this.creativeWorkRepository.existsById(id);
    }

    @Override
    public boolean existsByRegistrationCode(String registrationCode) {
        return this.creativeWorkRepository.existsByRegistrationCode(registrationCode);
    }

    @Override
    public CreativeWork create(CreativeWork creativeWork) {
        CreativeWorkEntity creativeWorkEntity = new CreativeWorkEntity(creativeWork);
        this.creativeWorkRepository.save(creativeWorkEntity);
        return creativeWork;
    }

    @Override
    public java.util.List<es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary> generateClaimSummaries() {
        return this.creativeWorkRepository.generateClaimSummaries();
    }

    @Override
    public java.util.List<CreativeWork> find(es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria criteria) {
        org.springframework.data.jpa.domain.Specification<CreativeWorkEntity> spec = (root, query, builder) -> builder.conjunction();

        if (criteria.getAuthorPenName() != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("authorPenName"), criteria.getAuthorPenName()));
        }

        if (criteria.getIsHighlyValued() != null) {
            spec = spec.and((root, query, builder) -> criteria.getIsHighlyValued()
                    ? builder.greaterThan(root.get("estimatedValuation"), new java.math.BigDecimal("10000"))
                    : builder.lessThanOrEqualTo(root.get("estimatedValuation"), new java.math.BigDecimal("10000")));
        }

        if (criteria.getClaimUrgent() != null) {
            spec = spec.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(root.join("claims").get("urgent"), criteria.getClaimUrgent());
            });
        }

        return this.creativeWorkRepository.findAll(spec).stream()
                .map(CreativeWorkEntity::toDomain)
                .toList();
    }
}
