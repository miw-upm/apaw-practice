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
        deadline.setLawyer(this.userFinder.read(creation.getUserId()));
        deadline.doCalculate();
        return this.deadlineGateway.create(deadline);
    }

    public List<DeadlineWorkloadReport> findWorkloadReport() {
        List<DeadlineWorkloadReport> reports = this.deadlineGateway.findWorkloadReport(LocalDate.now());
        if (reports.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = reports.stream()
                .map(report -> report.lawyer().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.findUsersById(userIds);
        return reports.stream()
                .map(report -> this.withLawyer(report, usersById))
                .toList();
    }

    private DeadlineWorkloadReport withLawyer(
            DeadlineWorkloadReport report, Map<UUID, UserSnapshot> usersById) {
        UUID lawyerId = report.lawyer().getId();
        UserSnapshot lawyer = usersById.get(lawyerId);
        if (lawyer == null) {
            throw new NotFoundException("User id not found: " + lawyerId);
        }
        return new DeadlineWorkloadReport(
                lawyer,
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
                .map(deadline -> deadline.getLawyer().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.findUsersById(userIds);
        return deadlines.stream()
                .map(deadline -> this.withLawyer(deadline, usersById))
                .filter(deadline -> this.matchesUserMobile(criteria, deadline))
                .map(Deadline::ofSummary)
                .toList();
    }

    private Deadline withLawyer(Deadline deadline, Map<UUID, UserSnapshot> usersById) {
        UUID lawyerId = deadline.getLawyer().getId();
        UserSnapshot lawyer = usersById.get(lawyerId);
        if (lawyer == null) {
            throw new NotFoundException("User id not found: " + lawyerId);
        }
        deadline.setLawyer(lawyer);
        return deadline;
    }

    private Map<UUID, UserSnapshot> findUsersById(Set<UUID> userIds) {
        return this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
    }

    private boolean matchesUserMobile(DeadlineFindCriteria criteria, Deadline deadline) {
        return !criteria.hasUserMobile()
                || criteria.getUserMobile().equals(deadline.getLawyer().getMobile());
    }
}
