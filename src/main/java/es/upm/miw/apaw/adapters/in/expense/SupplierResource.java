package es.upm.miw.apaw.adapters.in.expense;

import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import es.upm.miw.apaw.domain.services.expense.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(SupplierResource.SUPPLIERS)
@RequiredArgsConstructor
public class SupplierResource {
    public static final String SUPPLIERS = "/expense/suppliers";
    public static final String ID_ID = "/{id}";
    public static final String REPORT = "/report";

    private final SupplierService supplierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier create(@Valid @RequestBody Supplier supplier) {
        return this.supplierService.create(supplier);
    }

    @GetMapping(ID_ID)
    public Supplier read(@PathVariable UUID id) {
        return this.supplierService.read(id);
    }

    @PutMapping(ID_ID)
    public Supplier update(@PathVariable UUID id, @Valid @RequestBody Supplier supplier) {
        return this.supplierService.update(id, supplier);
    }

    @DeleteMapping(ID_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.supplierService.delete(id);
    }

    @GetMapping
    public List<Supplier> findAll() {
        return this.supplierService.findAll();
    }

    @PatchMapping(ID_ID)
    public Supplier patch(@PathVariable UUID id, @RequestBody Supplier supplier) {
        return this.supplierService.patch(id, supplier);
    }

    @GetMapping(REPORT)
    public List<SupplierExpenseReport> findExpenseReport() {
        return this.supplierService.findExpenseReport();
    }
}