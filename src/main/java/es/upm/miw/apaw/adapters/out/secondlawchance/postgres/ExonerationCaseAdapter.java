package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCaseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.ExonerationCaseGateway;
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
public class ExonerationCaseAdapter implements ExonerationCaseGateway {
    private final ExonerationCaseRepository exonerationCaseRepository;
    private final DebtRepository debtRepository;

    @Override
    @Transactional
    public ExonerationCase create(ExonerationCase exonerationCase) {
        ExonerationCaseEntity exonerationCaseEntity = new ExonerationCaseEntity(exonerationCase);
        exonerationCaseEntity.setDebts(exonerationCase.getDebts().stream()
                .map(debt -> this.debtRepository.getReferenceById(debt.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.exonerationCaseRepository.save(exonerationCaseEntity);
        return exonerationCase;
    }

    @Override
    public boolean existsByCaseNumber(String caseNumber) {
        return this.exonerationCaseRepository.existsByCaseNumber(caseNumber);
    }

    @Override
    public List<ExonerationCase> find(ExonerationCaseFindCriteria criteria) {
        Specification<ExonerationCaseEntity> specification = this.buildSpecification(criteria);
        return this.exonerationCaseRepository.findAll(specification, Sort.by("caseNumber")).stream()
                .map(this::toDomainWithoutDebts)
                .toList();
    }

    private ExonerationCase toDomainWithoutDebts(ExonerationCaseEntity entity) {
        ExonerationCase exonerationCase = new ExonerationCase();
        BeanUtils.copyProperties(entity, exonerationCase, "debts", "userId");
        exonerationCase.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return exonerationCase;
    }

    private Specification<ExonerationCaseEntity> buildSpecification(ExonerationCaseFindCriteria criteria) {
        Specification<ExonerationCaseEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasLawyer()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(builder.lower(root.get("lawyer")), criteria.getLawyer().toLowerCase()));
        }
        if (criteria.hasOpened()) {
            specification = specification.and((root, query, builder) -> criteria.getOpened()
                    ? builder.isNull(root.get("resolutionDate")) : builder.isNotNull(root.get("resolutionDate")));
        }
        return this.addCreditorType(specification, criteria.getCreditorType());
    }

    private Specification<ExonerationCaseEntity> addCreditorType(
            Specification<ExonerationCaseEntity> specification, CreditorType creditorType) {
        if (creditorType == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("debts").get("type"), creditorType);
        });
    }
}
