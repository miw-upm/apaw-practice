package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiskExposureReportService {
    private final ComplianceAssessmentGateway complianceAssessmentGateway;
    private final UserFinder userFinder;

    public List<RiskExposureReport> findReport() {
        List<RiskExposureReport> reports = this.complianceAssessmentGateway.findRiskExposureReport();
        Set<UUID> userIds = reports.stream()
                .map(RiskExposureReport::getUserSnapshotId)
                .filter(id -> id != null)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Map<UUID, UserSnapshot> usersById = userIds.isEmpty()
                ? Map.of()
                : this.findUsersById(userIds);
        return reports.stream()
                .map(report -> RiskExposureReport.from(report, usersById.get(report.getUserSnapshotId())))
                .toList();
    }

    private Map<UUID, UserSnapshot> findUsersById(Set<UUID> userIds) {
        List<UserSnapshot> users = this.userFinder.findByIds(userIds);
        if (users == null) {
            return Map.of();
        }
        return users.stream()
                .filter(user -> user != null && user.getId() != null)
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity(), (first, ignored) -> first));
    }
}
