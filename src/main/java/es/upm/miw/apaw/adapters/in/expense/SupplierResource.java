package es.upm.miw.apaw.adapters.in.expense;

import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.services.expense.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SupplierResource.SUPPLIERS)
public class SupplierResource {
    public static final String SUPPLIERS = "/expense/suppliers";
    public static final String ID_ID = "/{id}";

    private final SupplierService supplierService;

    @Autowired
    public SupplierResource(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier create(@Valid @RequestBody Supplier supplier) {
        return this.supplierService.create(supplier);
    }

    @GetMapping(ID_ID)
    public Supplier read(@PathVariable UUID id) {
        return this.supplierService.read(id);
    }
}