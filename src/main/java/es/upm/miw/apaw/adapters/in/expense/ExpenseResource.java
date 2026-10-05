package es.upm.miw.apaw.adapters.in.expense;

import es.upm.miw.apaw.adapters.in.expense.ExpenseCreationDto;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.services.expense.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExpenseResource.EXPENSES)
public class ExpenseResource {
    public static final String EXPENSES = "/expense/expenses";

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseResource(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@Valid @RequestBody ExpenseCreationDto dto) {
        return this.expenseService.create(dto);
    }
}