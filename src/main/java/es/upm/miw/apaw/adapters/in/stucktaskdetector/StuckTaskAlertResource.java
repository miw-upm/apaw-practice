package es.upm.miw.apaw.adapters.in.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.services.stucktaskdetector.StuckTaskAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(StuckTaskAlertResource.STUCK_TASK_ALERTS)
@RequiredArgsConstructor
public class StuckTaskAlertResource {

    public static final String STUCK_TASK_ALERTS = "/stuck-task-alerts";

    private final StuckTaskAlertService stuckTaskAlertService;
    public static final String ID = "/{id}";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StuckTaskAlert create(@Valid @RequestBody StuckTaskAlertCreation creation) {
        return this.stuckTaskAlertService.create(creation);
    }

    @GetMapping(ID)
    public StuckTaskAlert read(@PathVariable UUID id) {
        return this.stuckTaskAlertService.read(id);
    }

    @PutMapping(ID)
    public StuckTaskAlert update(@PathVariable UUID id, @Valid @RequestBody StuckTaskAlert stuckTaskAlert) {
        return this.stuckTaskAlertService.update(id, stuckTaskAlert);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.stuckTaskAlertService.delete(id);
    }
}
