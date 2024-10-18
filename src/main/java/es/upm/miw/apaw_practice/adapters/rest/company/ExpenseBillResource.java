package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.services.company.ExpenseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExpenseBillResource.EXPENSE_BILLS)
public class ExpenseBillResource {
    static final String EXPENSE_BILLS = "/company/expense-bills";
    static final String ID_ID = "/{id}";

    private final ExpenseBillService expenseBillService;

    @Autowired
    public ExpenseBillResource(ExpenseBillService expenseBillService) {
        this.expenseBillService = expenseBillService;
    }

    @DeleteMapping(ID_ID)
    public ResponseEntity<Void> deleteExpenseBill(@PathVariable String id) {
        this.expenseBillService.delete(id);
        return ResponseEntity.noContent().build(); // 返回204 NO_CONTENT
    }
}