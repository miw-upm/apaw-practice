package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SupplierService {

    private final SupplierGateway supplierGateway;

    @Autowired
    public SupplierService(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    public Supplier create(Supplier supplier) {
        if (this.supplierGateway.existsByTaxId(supplier.getTaxId())) {
            throw new ConflictException("Supplier taxId already exists: " + supplier.getTaxId());
        }
        supplier.doDefault();
        return this.supplierGateway.create(supplier);
    }

    public Supplier read(final UUID id) {
        log.debug("Reading supplier with ID: {}", id);
        return this.supplierGateway.readById(id)
                .orElseThrow(() -> {
                    log.warn("Supplier not found with ID: {}", id);
                    return new NotFoundException("Supplier id not found: " + id);
                });
    }
}