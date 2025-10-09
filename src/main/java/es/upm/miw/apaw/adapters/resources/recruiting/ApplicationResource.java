package es.upm.miw.apaw.adapters.resources.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.services.recruiting.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApplicationResource.APPLICATIONS)
public class ApplicationResource {

    public static final String APPLICATIONS = "/recruiting/applications";
    public static final String ID_ID = "/{id}";
    public static final String MEETINGS = "/meetings";

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationResource(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PutMapping(ID_ID + MEETINGS)
    public Application updateMeetings(@PathVariable UUID id, @Valid @RequestBody List<Meeting> meetingList) {
        return this.applicationService.updateMeetings(id, meetingList);
    }
}