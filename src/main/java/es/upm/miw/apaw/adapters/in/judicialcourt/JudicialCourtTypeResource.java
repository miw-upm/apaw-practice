package es.upm.miw.apaw.adapters.in.judicialcourt;

import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.services.judicialcourt.JudicialCourtTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
@RequiredArgsConstructor
public class JudicialCourtTypeResource {
    public static final String JUDICIAL_COURT_TYPES = "/judicial-court-types";
    public static final String ID = "/{id}";

    private final JudicialCourtTypeService judicialCourtTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JudicialCourtType create(@Valid @RequestBody JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeService.create(judicialCourtType);
    }

    @GetMapping(ID)
    public JudicialCourtType read(@PathVariable UUID id) {
        return this.judicialCourtTypeService.read(id);
    }

    @PutMapping(ID)
    public JudicialCourtType update(@PathVariable UUID id, @Valid @RequestBody JudicialCourtType judicialCourtType) {
        return this.judicialCourtTypeService.update(id, judicialCourtType);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.judicialCourtTypeService.delete(id);
    }
}
