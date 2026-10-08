package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.deadlinecalculator.CreationDeadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineFindCriteria;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.DeadlineGateway;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeadlineService {
    private final DeadlineGateway deadlineGateway;
    private final NonWorkingDayGateway nonWorkingDayGateway;
    private final UserFinder userFinder;

    public Deadline create(CreationDeadline creation) {
        if (this.deadlineGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Deadline title already exists: " + creation.getTitle());
        }
        Deadline deadline = new Deadline();
        BeanUtils.copyProperties(creation, deadline);
        deadline.doDefault();
        deadline.setNonWorkingDays(deadline.hasWorkingDayCount()
                ? this.nonWorkingDayGateway.findApplicable(creation.getRegion(), creation.getCity())
                : List.of());
        deadline.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        deadline.doCalculate();
        return this.deadlineGateway.create(deadline);
    }

    public List<DeadlineWorkloadReport> findWorkloadReport() {
        List<DeadlineWorkloadReport> reports = this.deadlineGateway.findWorkloadReport(LocalDate.now());
        if (reports.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = reports.stream()
                .map(DeadlineWorkloadReport::userId)
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return reports.stream()
                .map(report -> this.withUserSnapshot(report, usersById))
                .toList();
    }

    private DeadlineWorkloadReport withUserSnapshot(
            DeadlineWorkloadReport report, Map<UUID, UserSnapshot> usersById) {
        UserSnapshot userSnapshot = usersById.get(report.userId());
        if (userSnapshot == null) {
            throw new NotFoundException("User id not found: " + report.userId());
        }
        return new DeadlineWorkloadReport(
                report.userId(),
                userSnapshot,
                report.expiredDeadlineCount(),
                report.totalDeadlineCount(),
                report.holidayAffectedDeadlineCount());
    }

    public List<Deadline> find(DeadlineFindCriteria criteria) {
        List<Deadline> deadlines = this.deadlineGateway.find(criteria, LocalDate.now());
        if (deadlines.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = deadlines.stream()
                .map(deadline -> deadline.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return deadlines.stream()
                .map(deadline -> this.withUserSnapshot(deadline, usersById))
                .filter(deadline -> this.matchesUserMobile(criteria, deadline))
                .map(Deadline::ofSummary)
                .toList();
    }

    private Deadline withUserSnapshot(Deadline deadline, Map<UUID, UserSnapshot> usersById) {
        UUID userId = deadline.getUserSnapshot().getId();
        UserSnapshot userSnapshot = usersById.get(userId);
        if (userSnapshot == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        deadline.setUserSnapshot(userSnapshot);
        return deadline;
    }

    private boolean matchesUserMobile(DeadlineFindCriteria criteria, Deadline deadline) {
        return !criteria.hasUserMobile()
                || criteria.getUserMobile().equals(deadline.getUserSnapshot().getMobile());
    }
}
