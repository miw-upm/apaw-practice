
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}