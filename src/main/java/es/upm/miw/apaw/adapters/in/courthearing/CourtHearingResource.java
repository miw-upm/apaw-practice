package es.upm.miw.apaw.adapters.in.courthearing;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.model.courthearing.CreationCourtHearing;
import es.upm.miw.apaw.domain.services.courthearing.CourtHearingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CourtHearingResource.COURT_HEARINGS)
@RequiredArgsConstructor
public class CourtHearingResource {
    public static final String COURT_HEARINGS = "/court-hearings";

    private final CourtHearingService courtHearingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourtHearing create(@Valid @RequestBody CreationCourtHearing creation) {
        return this.courtHearingService.create(creation);
    }
}