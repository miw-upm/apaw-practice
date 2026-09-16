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
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        List<LegalProcedure> legalProcedures = this.legalProcedureGateway.find(criteria);
        if (legalProcedures.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = legalProcedures.stream()
                .map(legalProcedure -> legalProcedure.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, legalProcedures, this.userFinder.findByIds(userIds));
    }

    private List<LegalProcedure> toSummaries(
            LegalProcedureFindCriteria criteria,
            List<LegalProcedure> legalProcedures,
            List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return legalProcedures.stream()
                .map(legalProcedure -> this.enrichUserSnapshot(legalProcedure, usersById))
                .filter(legalProcedure -> this.matchesUserMobile(criteria, legalProcedure))
                .map(LegalProcedure::ofSummary)
                .toList();
    }

    private LegalProcedure enrichUserSnapshot(
            LegalProcedure legalProcedure, Map<UUID, UserSnapshot> usersById) {
        UUID userId = legalProcedure.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        legalProcedure.setUserSnapshot(user);
        return legalProcedure;
    }

    private boolean matchesUserMobile(
            LegalProcedureFindCriteria criteria, LegalProcedure legalProcedure) {
        return !criteria.hasUserMobile()
                || criteria.getUserMobile().equals(legalProcedure.getUserSnapshot().getMobile());
    }

    private LegalTask readLegalTask(UUID id) {
        return this.legalTaskGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal task id not found: " + id));
    }
}

