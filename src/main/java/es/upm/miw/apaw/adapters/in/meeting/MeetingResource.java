package es.upm.miw.apaw.adapters.in.meeting;

import es.upm.miw.apaw.domain.model.meeting.CreationMeeting;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingFindCriteria;
import es.upm.miw.apaw.domain.services.meeting.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MeetingResource.MEETINGS)
@RequiredArgsConstructor
public class MeetingResource {
    public static final String MEETINGS = "/meetings";

    private final MeetingService meetingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Meeting create(@Valid @RequestBody CreationMeeting creation) {
        return this.meetingService.create(creation);
    }

    @GetMapping
    public List<Meeting> find(@ModelAttribute MeetingFindCriteria criteria) {
        return this.meetingService.find(criteria);
    }
}
