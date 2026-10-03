package es.upm.miw.apaw.adapters.in.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.services.expertdirectoryservices.ExpertServiceScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExpertServiceScheduleResource.EXPERT_SERVICE_SCHEDULES)
@RequiredArgsConstructor
public class ExpertServiceScheduleResource {

    public static final String EXPERT_SERVICE_SCHEDULES = "/expert-directory-services/expert-service-schedules";

    private final ExpertServiceScheduleService expertServiceScheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpertServiceSchedule create(@Valid @RequestBody CreationExpertServiceSchedule creation) {
        return this.expertServiceScheduleService.create(creation);
    }
}