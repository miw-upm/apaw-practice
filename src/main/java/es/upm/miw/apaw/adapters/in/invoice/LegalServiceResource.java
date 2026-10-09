
package es.upm.miw.apaw.adapters.in.invoice;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.services.invoice.LegalServiceService;
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
@RequestMapping(LegalServiceResource.LEGAL_SERVICES)
@RequiredArgsConstructor
public class LegalServiceResource {

    public static final String LEGAL_SERVICES = "/legal-services";
    public static final String ID = "/{id}";

    private final LegalServiceService legalServiceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalService create(@Valid @RequestBody LegalService legalService) {
        return this.legalServiceService.create(legalService);
    }

    @GetMapping(ID)
    public LegalService read(@PathVariable UUID id) {
        return this.legalServiceService.read(id);
    }

    @PutMapping(ID)
    public LegalService update(@PathVariable UUID id, @Valid @RequestBody LegalService legalService) {
        return this.legalServiceService.update(id, legalService);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.legalServiceService.delete(id);
    }
}