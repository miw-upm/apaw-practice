package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.CreationExpense;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.ExpenseFindCriteria;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseGateway expenseGateway;
    private final SupplierGateway supplierGateway;
    private final UserFinder userFinder;

    public Expense create(CreationExpense creation) {
        if (this.expenseGateway.existsByReference(creation.getReference())) {
            throw new ConflictException("Expense reference already exists: " + creation.getReference());
        }
        Expense expense = new Expense();
        BeanUtils.copyProperties(creation, expense);
        expense.setSupplier(this.readSupplier(creation.getSupplierId()));
        expense.setUserSnapshot(this.userFinder.read(creation.getApplicantId()));
        expense.doDefault();
        return this.expenseGateway.create(expense);
    }

    public List<Expense> find(ExpenseFindCriteria criteria) {
        List<Expense> expenses = this.expenseGateway.find(criteria);
        if (expenses.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = expenses.stream()
                .map(expense -> expense.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, expenses, this.userFinder.findByIds(userIds));
    }

    private List<Expense> toSummaries(
            ExpenseFindCriteria criteria,
            List<Expense> expenses,
            List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return expenses.stream()
                .map(expense -> this.enrichUserSnapshot(expense, usersById))
                .filter(expense -> this.matchesUserMobile(criteria, expense))
                .map(Expense::ofSummary)
                .toList();
    }

    private Expense enrichUserSnapshot(Expense expense, Map<UUID, UserSnapshot> usersById) {
        UUID userId = expense.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        expense.setUserSnapshot(user);
        return expense;
    }

    private boolean matchesUserMobile(ExpenseFindCriteria criteria, Expense expense) {
        return !criteria.hasUserMobile()
                || criteria.getUserMobile().equals(expense.getUserSnapshot().getMobile());
    }

    private Supplier readSupplier(UUID id) {
        return this.supplierGateway.readById(id)
                .orElseThrow(() -> new NotFoundException("Supplier id not found: " + id));
    }
}