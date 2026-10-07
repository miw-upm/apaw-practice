package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertFindCriteria;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskAlertGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StuckTaskAlertAdapter implements StuckTaskAlertGateway {

    private final StuckTaskAlertRepository stuckTaskAlertRepository;
    private final StuckTaskRuleRepository stuckTaskRuleRepository;

    @Override
    @Transactional
    public StuckTaskAlert create(StuckTaskAlert stuckTaskAlert) {
        StuckTaskAlertEntity entity = new StuckTaskAlertEntity(stuckTaskAlert);
        entity.setStuckTaskRule(this.stuckTaskRuleRepository.getReferenceById(stuckTaskAlert.getStuckTaskRule().getId()));
        this.stuckTaskAlertRepository.save(entity);
        return stuckTaskAlert;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StuckTaskAlert> read(UUID id) {
        return this.stuckTaskAlertRepository.findById(id).map(StuckTaskAlertEntity::toDomain);
    }

    @Override
    public boolean existsByReference(String reference) {
        return this.stuckTaskAlertRepository.existsByReference(reference);
    }

    @Override
    @Transactional
    public StuckTaskAlert update(StuckTaskAlert stuckTaskAlert) {
        StuckTaskAlertEntity entity = this.stuckTaskAlertRepository.save(new StuckTaskAlertEntity(stuckTaskAlert));
        return entity.toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.stuckTaskAlertRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StuckTaskAlert> findAll() {
        return this.stuckTaskAlertRepository.findAllByOrderByDetectedAtAscIdAsc().stream()
                .map(StuckTaskAlertEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StuckTaskAlert> find(StuckTaskAlertFindCriteria criteria) {
        return this.stuckTaskAlertRepository
                .findAll(this.buildSpecification(criteria), Sort.by("detectedAt", "id")).stream()
                .map(StuckTaskAlertEntity::toDomain)
                .toList();
    }

    private Specification<StuckTaskAlertEntity> buildSpecification(StuckTaskAlertFindCriteria criteria) {
        Specification<StuckTaskAlertEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasEscalated()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("escalated"), criteria.getEscalated()));
        }
        if (criteria.hasProcedureKeyword()) {
            specification = specification.and((root, query, builder) -> builder.equal(
                    root.get("stuckTaskRule").get("procedureKeyword"), criteria.getProcedureKeyword()));
        }
        if (criteria.hasWithPenalty()) {
            specification = specification.and((root, query, builder) -> criteria.getWithPenalty()
                    ? builder.isNotNull(root.get("stuckTaskRule").get("penaltyAmount"))
                    : builder.isNull(root.get("stuckTaskRule").get("penaltyAmount")));
        }
        return specification;
    }
}
