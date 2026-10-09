package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LegalServiceAdapter implements LegalServiceGateway {

    private final LegalServiceRepository legalServiceRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public LegalService create(LegalService legalService) {
        return this.legalServiceRepository
                .save(new LegalServiceEntity(legalService))
                .toDomain();
    }

    @Override
    public Optional<LegalService> read(UUID id) {
        return this.legalServiceRepository.findById(id)
                .map(LegalServiceEntity::toDomain);
    }

    @Override
    public LegalService update(LegalService legalService) {
        return this.legalServiceRepository
                .save(new LegalServiceEntity(legalService))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.legalServiceRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.invoiceRepository.existsByServices_Id(id);
    }

    @Override
    public boolean existsByName(String name) {
        return this.legalServiceRepository.existsByName(name);
    }
}