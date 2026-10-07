package es.upm.miw.apaw.adapters.in.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleCreation;
import es.upm.miw.apaw.domain.services.stucktaskdetector.StuckTaskRuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(StuckTaskRuleResource.STUCK_TASK_RULES)
@RequiredArgsConstructor
public class StuckTaskRuleResource {

    public static final String STUCK_TASK_RULES = "/stuck-task-rules";
    public static final String REPORT = "/report";
    private final StuckTaskRuleService stuckTaskRuleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StuckTaskRule create(@Valid @RequestBody StuckTaskRuleCreation creation) {
        return this.stuckTaskRuleService.create(creation);
    }

    @GetMapping(REPORT)
    public List<StuckTaskRuleAlertReport> findAlertReport() {
        return this.stuckTaskRuleService.findAlertReport();
    }
}
