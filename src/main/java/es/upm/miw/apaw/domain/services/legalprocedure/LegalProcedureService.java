package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.models.legalprocedure.CreationLegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedureFindCriteria;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalProcedureGateway;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        LegalProcedure legalProcedure = new LegalProcedure();
        BeanUtils.copyProperties(creation, legalProcedure);
        legalProcedure.setLegalTasks(creation.getLegalTaskIds().stream()
                .map(this::readLegalTask)
                .toList());
        legalProcedure.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        legalProcedure.doDefault();
        return this.legalProcedureGateway.create(legalProcedure);
    }

    public List<LegalProcedure> find(LegalProcedureFindCriteria criteria) {
        if (!criteria.hasUserMobile()) {
            return this.findSummaries(criteria, null);
        }
        UserSnapshot user = this.userFinder.findByMobile(criteria.getUserMobile()).orElse(null);
        if (user == null) {
            return List.of();
        }
        return this.findSummaries(criteria, user.getId());
    }

    private List<LegalProcedure> findSummaries(LegalProcedureFindCriteria criteria, UUID userId) {
        return this.legalProcedureGateway.find(criteria, userId).stream()
                .map(LegalProcedure::ofSummary)
                .toList();
    }

    private LegalTask readLegalTask(UUID id) {
        return this.legalTaskGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal task id not found: " + id));
    }
}
