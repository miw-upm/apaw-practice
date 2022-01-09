package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.models.training.ParticipantEmailUpdating;
import es.upm.miw.apaw_practice.domain.services.training.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ParticipantResource.PARTICIPANTS)
public class ParticipantResource {
    static final String PARTICIPANTS = "/training/participants";
    static final String ID_ID = "{id}";

    static final String SEARCH = "/search";

    private final ParticipantService participantService;

    @Autowired
    public ParticipantResource(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public Stream<Participant> readAll() {
        return this.participantService.readAll();
    }

    @PatchMapping
    public void updateEmail(@RequestBody List<ParticipantEmailUpdating> participantEmailUpdatingList) {
        this.participantService.updateEmail(participantEmailUpdatingList);
    }
}
