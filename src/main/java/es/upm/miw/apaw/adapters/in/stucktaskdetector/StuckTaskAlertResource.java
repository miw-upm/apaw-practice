package es.upm.miw.apaw.adapters.in.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.services.stucktaskdetector.StuckTaskAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StuckTaskAlertResource.STUCK_TASK_ALERTS)
@RequiredArgsConstructor
public class StuckTaskAlertResource {

    public static final String STUCK_TASK_ALERTS = "/stuck-task-alerts";

    private final StuckTaskAlertService stuckTaskAlertService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StuckTaskAlert create(@Valid @RequestBody StuckTaskAlertCreation creation) {
        return this.stuckTaskAlertService.create(creation);
    }
}
