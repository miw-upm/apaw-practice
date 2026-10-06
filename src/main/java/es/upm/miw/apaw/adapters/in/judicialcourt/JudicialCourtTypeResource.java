package es.upm.miw.apaw.adapters.in.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.services.judicialcourt.JudicialCourtTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
@RequiredArgsConstructor
public class JudicialCourtTypeResource {
    public static final String JUDICIAL_COURT_TYPES = "/judicial-court-types";

    private final JudicialCourtTypeService judicialCourtTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JudicialCourtType create(@Valid @RequestBody JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeService.create(judicialCourtType);
    }
}
