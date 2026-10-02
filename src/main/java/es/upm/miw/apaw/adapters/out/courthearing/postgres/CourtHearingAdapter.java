package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtHearingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingFindCriteria;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class CourtHearingAdapter implements CourtHearingGateway {
    private final CourtHearingRepository courtHearingRepository;
    private final CourtRepository courtRepository;

    @Override
    public CourtHearing create(UUID courtId, CourtHearing courtHearing) {
        CourtHearingEntity entity = new CourtHearingEntity(courtHearing);
        entity.setCourt(this.courtRepository.getReferenceById(courtId));
        this.courtHearingRepository.save(entity);
        return courtHearing;
    }

    @Override
    public List<CourtHearing> find(CourtHearingFindCriteria criteria) {
        Specification<CourtHearingEntity> specification = this.buildSpecification(criteria);
        return this.courtHearingRepository.findAll(specification, Sort.by("date")).stream()
                .map(CourtHearingEntity::toDomain)
                .toList();
    }

    private Specification<CourtHearingEntity> buildSpecification(CourtHearingFindCriteria criteria) {
        Specification<CourtHearingEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasDate()) {
            specification = specification.and(this.onDate(criteria.getDate()));
        }
        if (criteria.hasScheduled()) {
            specification = specification.and(this.byScheduled(criteria.getScheduled()));
        }
        if (criteria.hasCourtCity()) {
            specification = specification.and(this.byCourtCity(criteria.getCourtCity()));
        }
        return specification;
    }

    private Specification<CourtHearingEntity> onDate(LocalDate date) {
        return (root, query, builder) -> builder.and(
                builder.greaterThanOrEqualTo(root.<LocalDateTime>get("date"), date.atStartOfDay()),
                builder.lessThan(root.<LocalDateTime>get("date"), date.plusDays(1).atStartOfDay()));
    }

    private Specification<CourtHearingEntity> byScheduled(boolean scheduled) {
        return (root, query, builder) -> scheduled
                ? builder.equal(root.get("status"), CourtHearingStatus.SCHEDULED)
                : builder.notEqual(root.get("status"), CourtHearingStatus.SCHEDULED);
    }

    private Specification<CourtHearingEntity> byCourtCity(String courtCity) {
        return (root, query, builder) -> builder.equal(root.join("court").get("city"), courtCity);
    }
}