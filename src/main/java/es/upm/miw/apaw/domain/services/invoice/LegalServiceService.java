
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceUpdate;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<LegalService> findAll() {
        return this.legalServiceGateway.findAll();
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

    @Transactional
    public void updateLegalServices(List<LegalServiceUpdate> updates) {
        this.assertUniqueIds(updates);

        List<LegalService> legalServices = updates.stream()
                .map(update -> {
                    LegalService legalService = this.read(update.id());
                    legalService.setFee(update.fee());
                    return legalService;
                })
                .toList();

        legalServices.forEach(this.legalServiceGateway::update);
    }

    private void assertUniqueIds(List<LegalServiceUpdate> updates) {
        Set<UUID> ids = new HashSet<>();

        for (LegalServiceUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException(
                        "Repeated legal service id: " + update.id());
            }
        }
    }

    public void delete(UUID id) {
        if (this.legalServiceGateway.isReferenced(id)) {
            throw new ConflictException(
                    "Legal service is referenced by an invoice: " + id);
        }
        this.legalServiceGateway.delete(id);
    }
}