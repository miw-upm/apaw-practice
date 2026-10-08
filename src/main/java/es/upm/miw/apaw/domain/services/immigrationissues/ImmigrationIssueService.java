package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssueFindCriteria;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.ImmigrationIssueGateway;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.LawBasisGateway;
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
public class ImmigrationIssueService {

    private final ImmigrationIssueGateway immigrationIssueGateway;
    private final LawBasisGateway lawBasisGateway;
    private final UserFinder userFinder;

    public ImmigrationIssue create(CreationImmigrationIssue creation) {
        if (this.immigrationIssueGateway.existsBySubject(creation.getSubject())) {
            throw new ConflictException("Immigration issue subject already exists: " + creation.getSubject());
        }
        ImmigrationIssue immigrationIssue = new ImmigrationIssue();
        BeanUtils.copyProperties(creation, immigrationIssue);
        immigrationIssue.setLawBases(creation.getLawBasisIds().stream()
                .map(this::readLawBasis)
                .toList());
        immigrationIssue.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        immigrationIssue.doDefault();
        return this.immigrationIssueGateway.create(immigrationIssue);
    }

    private LawBasis readLawBasis(UUID id) {
        return this.lawBasisGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Law basis id not found: " + id));
    }

    public List<ImmigrationIssue> find(ImmigrationIssueFindCriteria criteria) {
        List<ImmigrationIssue> immigrationIssues = this.immigrationIssueGateway.find(criteria);
        if (immigrationIssues.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = immigrationIssues.stream()
                .map(immigrationIssue -> immigrationIssue.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, immigrationIssues, this.userFinder.findByIds(userIds));
    }

    private List<ImmigrationIssue> toSummaries(
            ImmigrationIssueFindCriteria criteria,
            List<ImmigrationIssue> immigrationIssues,
            List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return immigrationIssues.stream()
                .filter(immigrationIssue -> this.matchesFamilyName(criteria, immigrationIssue, usersById))
                .map(immigrationIssue -> this.enrichUserSnapshot(immigrationIssue, usersById))
                .map(ImmigrationIssue::ofSummary)
                .toList();
    }

    private boolean matchesFamilyName(
            ImmigrationIssueFindCriteria criteria,
            ImmigrationIssue immigrationIssue,
            Map<UUID, UserSnapshot> usersById) {
        if (!criteria.hasFamilyName()) {
            return true;
        }
        UserSnapshot user = usersById.get(immigrationIssue.getUserSnapshot().getId());
        return user != null && criteria.getFamilyName().equalsIgnoreCase(user.getFamilyName());
    }

    private ImmigrationIssue enrichUserSnapshot(
            ImmigrationIssue immigrationIssue, Map<UUID, UserSnapshot> usersById) {
        UserSnapshot user = usersById.get(immigrationIssue.getUserSnapshot().getId());
        if (user != null) {
            immigrationIssue.setUserSnapshot(user);
        }
        return immigrationIssue;
    }

    public List<LawBasisUsageReport> findLawBasisUsageReport() {
        return this.immigrationIssueGateway.findLawBasisUsageReport();
    }
}