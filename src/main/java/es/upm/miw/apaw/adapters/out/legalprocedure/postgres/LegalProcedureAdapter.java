package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedureFindCriteria;
import es.upm.miw.apaw.domain.models.legalprocedure.TaskStatus;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalProcedureGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LegalProcedureAdapter implements LegalProcedureGateway {
    private final LegalProcedureRepository legalProcedureRepository;
    private final LegalTaskRepository legalTaskRepository;

    @Override
    @Transactional
    public LegalProcedure create(LegalProcedure legalProcedure) {
        LegalProcedureEntity legalProcedureEntity = new LegalProcedureEntity(legalProcedure);
        List<LegalTaskEntity> legalTaskEntities = legalProcedure.getLegalTasks().stream()
                .map(legalTask -> this.legalTaskRepository.getReferenceById(legalTask.getId()))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        legalProcedureEntity.setLegalTasks(legalTaskEntities);
        this.legalProcedureRepository.save(legalProcedureEntity);
        return legalProcedure;
    }

    @Override
    public List<LegalProcedure> find(LegalProcedureFindCriteria criteria) {
        Specification<LegalProcedureEntity> specification = this.buildSpecification(criteria);
        return this.legalProcedureRepository.findAll(specification, Sort.by("title")).stream()
                .map(this::toDomainWithoutLegalTasks)
                .toList();
    }

    private LegalProcedure toDomainWithoutLegalTasks(LegalProcedureEntity entity) {
        LegalProcedure legalProcedure = new LegalProcedure();
        BeanUtils.copyProperties(entity, legalProcedure, "legalTasks", "userId");
        legalProcedure.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return legalProcedure;
    }

    private Specification<LegalProcedureEntity> buildSpecification(LegalProcedureFindCriteria criteria) {
        Specification<LegalProcedureEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasVatIncluded()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("vatIncluded"), criteria.getVatIncluded()));
        }
        if (criteria.hasOpened()) {
            specification = specification.and((root, query, builder) -> criteria.getOpened()
                    ? builder.isNull(root.get("closingDate")) : builder.isNotNull(root.get("closingDate")));
        }
        specification = this.addTaskStatus(specification, criteria.getTaskStatus());
        return specification;
    }

    private Specification<LegalProcedureEntity> addTaskStatus(
            Specification<LegalProcedureEntity> specification, TaskStatus taskStatus) {
        if (taskStatus == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("legalTasks").get("taskStatus"), taskStatus);
        });
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.legalProcedureRepository.existsByTitle(title);
    }
}
