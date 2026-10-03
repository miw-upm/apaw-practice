package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SupplierAdapter implements SupplierGateway {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierAdapter(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier create(Supplier supplier) {
        SupplierEntity entity = new SupplierEntity();
        BeanUtils.copyProperties(supplier, entity);
        SupplierEntity saved = this.supplierRepository.save(entity);
        Supplier result = new Supplier();
        BeanUtils.copyProperties(saved, result);
        return result;
    }

    @Override
    public boolean existsByTaxId(String taxId) {
        return this.supplierRepository.existsByTaxId(taxId);
    }

    @Override
    public Optional<Supplier> readById(UUID id) {
        return this.supplierRepository.findById(id)
                .map(entity -> {
                    Supplier domain = new Supplier();
                    BeanUtils.copyProperties(entity, domain);
                    return domain;
                });
    }
}