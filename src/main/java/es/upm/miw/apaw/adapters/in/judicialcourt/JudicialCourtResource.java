package es.upm.miw.apaw.adapters.in.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.CreationJudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.services.judicialcourt.JudicialCourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(JudicialCourtResource.JUDICIAL_COURTS)
@RequiredArgsConstructor
public class JudicialCourtResource {
    public static final String JUDICIAL_COURTS = "/judicial-courts";

    private final JudicialCourtService judicialCourtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JudicialCourt create(@Valid @RequestBody CreationJudicialCourt creation) {
        return this.judicialCourtService.create(creation);
    }
}
