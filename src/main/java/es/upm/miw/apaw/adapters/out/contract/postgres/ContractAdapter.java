package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import es.upm.miw.apaw.domain.model.contract.ContractFindCriteria;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContractAdapter implements ContractGateway {
    private final ContractRepository contractRepository;
    private final ClauseRepository clauseRepository;

    @Override
    @Transactional
    public Contract create(Contract contract) {
        ContractEntity contractEntity = new ContractEntity(contract);

        List<ClauseEntity> clauseEntities = contract.getClauses().stream()
                .map(clause -> this.clauseRepository.getReferenceById(clause.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        contractEntity.setClauses(clauseEntities);

        return this.contractRepository.save(contractEntity).toDomain();
    }

    @Override
    public List<ContractExpirationReport> findExpirationReport(LocalDate today, LocalDate limitDate) {
        return this.contractRepository.findExpirationReport(today, limitDate);
    }

    @Override
    public List<Contract> find(ContractFindCriteria criteria) {
        Specification<ContractEntity> specification = this.buildSpecification(criteria);

        return this.contractRepository.findAll(
                        specification,
                        Sort.by("title")
                ).stream()
                .map(this::toDomainWithoutClauses)
                .toList();
    }

    private Contract toDomainWithoutClauses(ContractEntity entity) {
        Contract contract = new Contract();

        BeanUtils.copyProperties(
                entity,
                contract,
                "clauses",
                "userId"
        );

        contract.setUserSnapshot(
                UserSnapshot.builder()
                        .id(entity.getUserId())
                        .build()
        );

        return contract;
    }

    private Specification<ContractEntity> buildSpecification(
            ContractFindCriteria criteria) {

        Specification<ContractEntity> specification =
                (root, query, builder) -> builder.conjunction();

        if (criteria.hasTitle()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("title"), criteria.getTitle()));
        }

        if (criteria.hasActive()) {
            specification = specification.and((root, query, builder) ->
                    criteria.getActive()
                            ? builder.or(
                            builder.isNull(root.get("endDate")),
                            builder.greaterThanOrEqualTo(
                                    root.get("endDate"),
                                    LocalDate.now()
                            )
                    )
                            : builder.lessThan(
                            root.get("endDate"),
                            LocalDate.now()
                    ));
        }

        if (criteria.hasClauseType()) {
            specification = specification.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(
                        root.join("clauses").get("type"),
                        criteria.getClauseType()
                );
            });
        }

        return specification;
    }
}