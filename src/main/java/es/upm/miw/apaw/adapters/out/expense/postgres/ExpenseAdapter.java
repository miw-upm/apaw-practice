package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ExpenseAdapter implements ExpenseGateway {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseAdapter(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public boolean isSupplierInUse(UUID supplierId) {
        return this.expenseRepository.existsBySupplierEntity_Id(supplierId);
    }

    @Override
    public boolean existsByReference(String reference) {
        return this.expenseRepository.existsByReference(reference);
    }

    @Override
    public Expense create(Expense expense) {
        SupplierEntity supplierEntity = new SupplierEntity();
        BeanUtils.copyProperties(expense.getSupplier(), supplierEntity);

        ExpenseEntity entity = ExpenseEntity.builder()
                .id(expense.getId())
                .reference(expense.getReference())
                .amount(expense.getAmount())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .category(expense.getCategory())
                .isPaid(expense.getIsPaid())
                .supplierEntity(supplierEntity)
                .build();

        if (expense.getUserSnapshot() != null) {
            entity.setApplicantId(expense.getUserSnapshot().getId());
            entity.setApplicantFirstName(expense.getUserSnapshot().getFirstName());
            entity.setApplicantFamilyName(expense.getUserSnapshot().getFamilyName());
            entity.setApplicantEmail(expense.getUserSnapshot().getEmail());
        }

        ExpenseEntity saved = this.expenseRepository.save(entity);

        Supplier supplierDomain = new Supplier();
        BeanUtils.copyProperties(saved.getSupplierEntity(), supplierDomain);

        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(saved.getApplicantId())
                .firstName(saved.getApplicantFirstName())
                .familyName(saved.getApplicantFamilyName())
                .email(saved.getApplicantEmail())
                .build();

        return Expense.builder()
                .id(saved.getId())
                .reference(saved.getReference())
                .amount(saved.getAmount())
                .description(saved.getDescription())
                .expenseDate(saved.getExpenseDate())
                .category(saved.getCategory())
                .isPaid(saved.getIsPaid())
                .supplier(supplierDomain)
                .userSnapshot(userSnapshot)
                .build();
    }
}