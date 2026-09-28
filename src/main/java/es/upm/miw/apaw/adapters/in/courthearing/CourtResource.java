package es.upm.miw.apaw.adapters.in.courthearing;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.services.courthearing.CourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@RestController
@RequestMapping(CourtResource.COURTS)
@RequiredArgsConstructor
public class CourtResource {
    public static final String COURTS = "/courts";

    private final CourtService courtService;

    public static final String ID = "/{id}";


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Court create(@Valid @RequestBody Court court) {
        return this.courtService.create(court);
    }

    @GetMapping(ID)
    public Court read(@PathVariable UUID id) {
        return this.courtService.read(id);
    }
}