package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.EstateFindCriteria;
import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;
import es.upm.miw.apaw.domain.ports.out.probate.EstateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EstateAdapter implements EstateGateway {
    private final EstateRepository estateRepository;
    private final HeirRepository heirRepository;

    @Override
    @Transactional
    public Estate create(Estate estate) {
        EstateEntity entity = new EstateEntity(estate);
        entity.setHeirs(estate.getHeirs().stream()
                .map(heir -> this.heirRepository.getReferenceById(heir.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.estateRepository.save(entity);
        return estate;
    }

    @Override
    public boolean existsByFileNumber(String fileNumber) {
        return this.estateRepository.existsByFileNumber(fileNumber);
    }

    @Override
    public List<EstateUsageReport> heirStatusSummary() {
        return this.estateRepository.heirStatusSummary();
    }

    @Override
    public List<Estate> find(EstateFindCriteria criteria) {
        Specification<EstateEntity> specification = this.buildSpecification(criteria);
        return this.estateRepository.findAll(specification, Sort.by("fileNumber")).stream()
                .map(this::toDomain)
                .toList();
    }

    private Estate toDomain(EstateEntity entity) {
        Estate estate = new Estate();
        BeanUtils.copyProperties(entity, estate, "heirs", "userId");
        estate.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return estate;
    }

    private Specification<EstateEntity> buildSpecification(EstateFindCriteria criteria) {
        Specification<EstateEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.appliesFileNumber()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("fileNumber"), criteria.getFileNumber()));
        }
        if (criteria.appliesOpened()) {
            specification = specification.and((root, query, builder) -> criteria.getOpened()
                    ? builder.isNull(root.get("closingDate"))
                    : builder.isNotNull(root.get("closingDate")));
        }
        if (criteria.appliesHeirStatus()) {
            specification = specification.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(root.join("heirs").get("heirStatus"), criteria.getHeirStatus());
            });
        }
        return specification;
    }
}
