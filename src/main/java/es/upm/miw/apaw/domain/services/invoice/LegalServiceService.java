
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LegalServiceService {

    private final LegalServiceGateway legalServiceGateway;

    public LegalService create(LegalService legalService) {
        legalService.doDefault();

        if (this.legalServiceGateway.existsByName(legalService.getName())) {
            throw new ConflictException(
                    "Legal service name already exists: " + legalService.getName());
        }

        return this.legalServiceGateway.create(legalService);
    }

    public LegalService read(UUID id) {
        return this.legalServiceGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("Legal service id not found: " + id));
    }

    public LegalService update(UUID id, LegalService legalService) {
        LegalService storedLegalService = this.read(id);

        if (!storedLegalService.getName().equals(legalService.getName())
                && this.legalServiceGateway.existsByName(legalService.getName())) {
            throw new ConflictException(
                    "Legal service name already exists: " + legalService.getName());
        }

        storedLegalService.setName(legalService.getName());
        storedLegalService.setDescription(legalService.getDescription());
        storedLegalService.setFee(legalService.getFee());
        storedLegalService.setRequiresAppointment(legalService.getRequiresAppointment());
        storedLegalService.setCategory(legalService.getCategory());
        storedLegalService.setLegalArea(legalService.getLegalArea());

        return this.legalServiceGateway.update(storedLegalService);
    }

    public void delete(UUID id) {
        if (this.legalServiceGateway.isReferenced(id)) {
            throw new ConflictException(
                    "Legal service is referenced by an invoice: " + id);
        }
        this.legalServiceGateway.delete(id);
    }
}