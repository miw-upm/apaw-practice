package es.upm.miw.apaw.adapters.resources.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.services.recruiting.AttendeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AttendeeResource.ATTENDEES)
public class AttendeeResource {

    public static final String ATTENDEES = "/recruiting/attendees";

    public static final String EMAIL_ID = "/{emailAddress}";

    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeResource(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping(EMAIL_ID)
    public Attendee read(@Valid @PathVariable String emailAddress) {
        return this.attendeeService.read(emailAddress);
    }
}