package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtFindCriteria;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JudicialCourtAdapter implements JudicialCourtGateway {
    private final JudicialCourtRepository judicialCourtRepository;

    @Override
    public JudicialCourt create(JudicialCourt judicialCourt) {
        return this.judicialCourtRepository.save(new JudicialCourtEntity(judicialCourt)).toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public List<JudicialCourt> find(JudicialCourtFindCriteria criteria) {
        Specification<JudicialCourtEntity> specification = this.buildSpecification(criteria);
        return this.judicialCourtRepository.findAll(specification).stream()
                .map(JudicialCourtEntity::toDomain)
                .toList();
    }

    private Specification<JudicialCourtEntity> buildSpecification(JudicialCourtFindCriteria criteria) {
        Specification<JudicialCourtEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria == null) {
            return specification;
        }
        if (criteria.hasCity()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("city"), criteria.getCity()));
        }
        if (criteria.hasCompleteContactInformation()) {
            specification = specification.and((root, query, builder) -> {
                if (Boolean.TRUE.equals(criteria.getCompleteContactInformation())) {
                    return builder.and(
                            builder.isNotNull(root.get("phone")),
                            builder.isNotNull(root.get("email")));
                }
                return builder.or(
                        builder.isNull(root.get("phone")),
                        builder.isNull(root.get("email")));
            });
        }
        if (criteria.hasJurisdiction()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.join("type").get("jurisdiction"), criteria.getJurisdiction()));
        }
        return specification;
    }

    @Override
    public List<LawyerCourtStat> findLawyerCourtStats() {
        return this.judicialCourtRepository.findLawyerCourtStats();
    }
}
