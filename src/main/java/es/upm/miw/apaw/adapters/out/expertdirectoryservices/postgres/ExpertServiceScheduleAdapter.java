package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria.ExpertServiceScheduleFindCriteria;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExpertServiceScheduleAdapter implements ExpertServiceScheduleGateway {

    private final ExpertServiceScheduleRepository expertServiceScheduleRepository;
    private final LegalExpertProfileRepository legalExpertProfileRepository;

    @Override
    @Transactional
    public ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule) {
        List<UUID> profileIds = expertServiceSchedule.getLegalExpertProfiles().stream()
                .map(LegalExpertProfile::getId)
                .toList();
        List<LegalExpertProfileEntity> profileEntities = this.legalExpertProfileRepository.findAllById(profileIds)
                .stream()
                .sorted(Comparator.comparingInt(profile -> profileIds.indexOf(profile.getId())))
                .toList();

        ExpertServiceScheduleEntity entity = new ExpertServiceScheduleEntity(expertServiceSchedule);
        entity.setLegalExpertProfiles(new ArrayList<>(profileEntities));
        return this.expertServiceScheduleRepository.save(entity).toDomain();
    }

    @Override
    public List<ExpertServiceSchedule> find(ExpertServiceScheduleFindCriteria criteria) {
        Specification<ExpertServiceScheduleEntity> specification = this.buildSpecification(criteria);
        return this.expertServiceScheduleRepository.findAll(specification, Sort.by("tariffCode")).stream()
                .map(ExpertServiceScheduleEntity::toDomain)
                .toList();
    }

    private Specification<ExpertServiceScheduleEntity> buildSpecification(
            ExpertServiceScheduleFindCriteria criteria) {
        Specification<ExpertServiceScheduleEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasMinRateAmount()) {
            specification = specification.and((root, query, builder) ->
                    builder.greaterThanOrEqualTo(root.get("rateAmount"), criteria.getMinRateAmount()));
        }
        if (criteria.hasWithSpecialCondition()) {
            specification = specification.and((root, query, builder) -> criteria.getWithSpecialCondition()
                    ? builder.isNotNull(root.get("specialCondition"))
                    : builder.isNull(root.get("specialCondition")));
        }
        if (criteria.hasSpecialtyArea()) {
            specification = specification.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(root.join("legalExpertProfiles").get("specialtyArea"),
                        criteria.getSpecialtyArea());
            });
        }
        return specification;
    }

    @Override
    public boolean existsByTariffCode(String tariffCode) {
        return this.expertServiceScheduleRepository.existsByTariffCode(tariffCode);
    }

    @Override
    public boolean existsByLegalExpertProfileIds(Collection<UUID> legalExpertProfileIds) {
        return this.expertServiceScheduleRepository.existsByLegalExpertProfilesIdIn(legalExpertProfileIds);
    }
}