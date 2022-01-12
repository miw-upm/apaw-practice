package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.services.training.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ParticipantResource.PARTICIPANTS)
public class ParticipantResource {
    static final String PARTICIPANTS = "/training/participants";

    static final String EMAIL = "/{email}";

    private final ParticipantService participantService;

    @Autowired
    public ParticipantResource(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping(EMAIL)
    public Participant read(@PathVariable String email) {
        return Participant.ofCourseIdentity(this.participantService.read(email));
    }
}
