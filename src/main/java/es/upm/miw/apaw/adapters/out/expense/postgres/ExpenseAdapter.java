package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.ExpenseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExpenseAdapter implements ExpenseGateway {

    private final ExpenseRepository expenseRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public Expense create(Expense expense) {
        ExpenseEntity expenseEntity = new ExpenseEntity(expense);
        SupplierEntity supplierEntity = this.supplierRepository.getReferenceById(expense.getSupplier().getId());
        expenseEntity.setSupplierEntity(supplierEntity);
        this.expenseRepository.save(expenseEntity);
        return expense;
    }

    @Override
    public boolean existsByReference(String reference) {
        return this.expenseRepository.existsByReference(reference);
    }

    @Override
    public boolean isSupplierInUse(UUID supplierId) {
        return this.expenseRepository.existsBySupplierEntity_Id(supplierId);
    }

    @Override
    public List<Expense> find(ExpenseFindCriteria criteria) {
        Specification<ExpenseEntity> specification = this.buildSpecification(criteria);
        return this.expenseRepository.findAll(specification, Sort.by("reference")).stream()
                .map(ExpenseEntity::toDomain)
                .toList();
    }

    private Specification<ExpenseEntity> buildSpecification(ExpenseFindCriteria criteria) {
        Specification<ExpenseEntity> specification = (root, query, builder) -> builder.conjunction();

        if (criteria.hasCategory()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("category"), criteria.getCategory()));
        }

        if (criteria.hasUnpaid()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("isPaid"), !criteria.getUnpaid()));
        }

        if (criteria.hasSupplierTaxId()) {
            specification = specification.and((root, query, builder) -> {
                query.distinct(true);
                return builder.equal(root.join("supplierEntity").get("taxId"), criteria.getSupplierTaxId());
            });
        }

        return specification;
    }
}