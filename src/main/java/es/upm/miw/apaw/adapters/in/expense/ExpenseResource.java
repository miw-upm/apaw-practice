package es.upm.miw.apaw.adapters.in.expense;

import es.upm.miw.apaw.domain.model.expense.CreationExpense;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.services.expense.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExpenseResource.EXPENSES)
@RequiredArgsConstructor
public class ExpenseResource {
    public static final String EXPENSES = "/expense/expenses";

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@Valid @RequestBody CreationExpense creation) {
        return this.expenseService.create(creation);
    }
}