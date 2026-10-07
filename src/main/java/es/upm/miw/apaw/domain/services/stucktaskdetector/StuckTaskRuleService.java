package es.upm.miw.apaw.domain.services.stucktaskdetector;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleCreation;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StuckTaskRuleService {

    private final StuckTaskRuleGateway stuckTaskRuleGateway;
    private final UserFinder userFinder;

    public StuckTaskRule create(StuckTaskRuleCreation creation) {
        if (this.stuckTaskRuleGateway.existsByName(creation.getName())) {
            throw new ConflictException("Stuck task rule name already exists: " + creation.getName());
        }
        StuckTaskRule stuckTaskRule = StuckTaskRule.builder()
                .name(creation.getName())
                .procedureKeyword(creation.getProcedureKeyword())
                .thresholdDays(creation.getThresholdDays())
                .penaltyAmount(creation.getPenaltyAmount())
                .active(creation.getActive())
                .createdByUser(this.userFinder.read(creation.getUserId()))
                .build();
        stuckTaskRule.doDefault();
        return this.stuckTaskRuleGateway.create(stuckTaskRule);
    }

    public List<StuckTaskRuleAlertReport> findAlertReport() {
        List<StuckTaskRuleAlertReport> report = this.stuckTaskRuleGateway.findAlertReport();
        if (report.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = report.stream()
                .map(item -> item.getCreatedByUser().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        report.forEach(item -> this.enrichCreatedByUser(item, usersById));
        return report;
    }

    private void enrichCreatedByUser(StuckTaskRuleAlertReport item, Map<UUID, UserSnapshot> usersById) {
        UUID userId = item.getCreatedByUser().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        item.setCreatedByUser(user);
    }
}