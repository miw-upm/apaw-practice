package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.models.legalprocedure.CreationLegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalProcedureGateway;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LegalProcedureService {
    private final LegalProcedureGateway legalProcedureGateway;
    private final LegalTaskGateway legalTaskGateway;
    private final UserFinder userFinder;

    public LegalProcedure create(CreationLegalProcedure creation) {
        if (this.legalProcedureGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Legal procedure title already exists: " + creation.getTitle());
        }
        List<LegalTask> legalTasks = creation.getLegalTaskIds().stream()
                .map(this::readLegalTask)
                .toList();
        UserSnapshot userSnapshot = this.userFinder.read(creation.getUserId());
        LegalProcedure legalProcedure = this.buildLegalProcedure(creation, legalTasks, userSnapshot);
        legalProcedure.doDefault();
        return this.legalProcedureGateway.create(legalProcedure);
    }

    private LegalProcedure buildLegalProcedure(CreationLegalProcedure creation, List<LegalTask> legalTasks,
                                               UserSnapshot userSnapshot) {
        return LegalProcedure.builder()
                .title(creation.getTitle())
                .closingDate(creation.getClosingDate())
                .budget(creation.getBudget())
                .budgetProposal(creation.getBudgetProposal())
                .vatIncluded(creation.getVatIncluded())
                .legalTasks(legalTasks)
                .userSnapshot(userSnapshot)
                .build();
    }

    private LegalTask readLegalTask(UUID id) {
        return this.legalTaskGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal task id not found: " + id));
    }
}
