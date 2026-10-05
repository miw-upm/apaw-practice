package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.OverdueAssessmentReport;
import es.upm.miw.apaw.domain.model.euregulation.OverdueComplianceReport;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplianceDeadlineReportService {
    private final ComplianceAssessmentGateway complianceAssessmentGateway;
    private final UserFinder userFinder;

    public List<OverdueComplianceReport> findReport() {
        List<OverdueAssessmentReport> reports = this.complianceAssessmentGateway.findOverdueAssessmentReport();
        Set<UUID> userIds = reports.stream()
                .map(OverdueAssessmentReport::getUserSnapshotId)
                .filter(id -> id != null)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Map<UUID, UserSnapshot> usersById = userIds.isEmpty()
                ? Map.of()
                : this.findUsersById(userIds);
        return reports.stream()
                .map(report -> OverdueComplianceReport.from(
                        report, usersById.get(report.getUserSnapshotId())))
                .toList();
    }

    private Map<UUID, UserSnapshot> findUsersById(Set<UUID> userIds) {
        List<UserSnapshot> users = this.userFinder.findByIds(userIds);
        if (users == null) {
            return Map.of();
        }
        return users.stream()
                .filter(user -> user != null && user.getId() != null)
                .collect(Collectors.toMap(
                        UserSnapshot::getId,
                        user -> user,
                        (first, ignored) -> first));
    }
}
