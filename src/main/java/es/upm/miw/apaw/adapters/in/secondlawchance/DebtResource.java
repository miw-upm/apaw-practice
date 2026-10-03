package es.upm.miw.apaw.adapters.in.secondlawchance;

import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.DebtPatch;
import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;
import es.upm.miw.apaw.domain.services.secondlawchance.DebtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(DebtResource.DEBTS)
@RequiredArgsConstructor
public class DebtResource {
    public static final String DEBTS = "/debts";
    public static final String ID = "/{id}";
    public static final String REPORT = "/report";

    private final DebtService debtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Debt create(@Valid @RequestBody Debt debt) {
        return this.debtService.create(debt);
    }

    @GetMapping
    public List<Debt> findAll() {
        return this.debtService.findAll();
    }

    @GetMapping(REPORT)
    public List<SharedDebtReport> findSharedReport() {
        return this.debtService.findSharedReport();
    }

    @GetMapping(ID)
    public Debt read(@PathVariable UUID id) {
        return this.debtService.read(id);
    }

    @PutMapping(ID)
    public Debt update(@PathVariable UUID id, @Valid @RequestBody Debt debt) {
        return this.debtService.update(id, debt);
    }

    @PatchMapping(ID)
    public Debt patch(@PathVariable UUID id, @Valid @RequestBody DebtPatch patch) {
        return this.debtService.patch(id, patch);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.debtService.delete(id);
    }
}
