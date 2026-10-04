package es.upm.miw.apaw.domain.services.stucktaskdetector;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleCreation;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}