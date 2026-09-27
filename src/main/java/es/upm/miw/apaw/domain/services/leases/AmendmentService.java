package es.upm.miw.apaw.domain.services.leases;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentUpdate;
import es.upm.miw.apaw.domain.ports.out.leases.AmendmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmendmentService {
    private final AmendmentGateway amendmentGateway;

    public Amendment create(Amendment amendment) {
        amendment.doDefault();
        return this.amendmentGateway.create(amendment);
    }

    public List<Amendment> findAll() {
        return this.amendmentGateway.findAll();
    }

    public Amendment read(UUID id) {
        return this.amendmentGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Amendment id not found: " + id));
    }

    public Amendment update(UUID id, Amendment amendment) {
        Amendment storedAmendment = this.read(id);
        storedAmendment.setAmendmentNumber(amendment.getAmendmentNumber());
        storedAmendment.setDescription(amendment.getDescription());
        storedAmendment.setEffectiveDate(amendment.getEffectiveDate());
        storedAmendment.setAdditionalAmount(amendment.getAdditionalAmount());
        storedAmendment.setApproved(amendment.getApproved() != null && amendment.getApproved());
        storedAmendment.setAmendmentType(amendment.getAmendmentType());
        return this.amendmentGateway.update(storedAmendment);
    }

    public Amendment patch(UUID id, AmendmentUpdate update) {
        Amendment storedAmendment = this.read(id);
        storedAmendment.patch(update);
        return this.amendmentGateway.update(storedAmendment);
    }

    public void delete(UUID id) {
        if (this.amendmentGateway.isReferenced(id)) {
            throw new ConflictException("Amendment is referenced by a lease: " + id);
        }
        this.amendmentGateway.delete(id);
    }
}
