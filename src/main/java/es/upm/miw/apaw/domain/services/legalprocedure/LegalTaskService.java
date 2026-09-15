package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LegalTaskService {
    private final LegalTaskGateway legalTaskGateway;

    public LegalTask create(LegalTask legalTask) {
        if (this.legalTaskGateway.existsByTitle(legalTask.getTitle())) {
            throw new ConflictException("Legal task title already exists: " + legalTask.getTitle());
        }
        return this.legalTaskGateway.create(legalTask);
    }
}
