
package es.upm.miw.apaw.adapters.in.invoice;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.services.invoice.LegalServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LegalServiceResource.LEGAL_SERVICES)
@RequiredArgsConstructor
public class LegalServiceResource {

    public static final String LEGAL_SERVICES = "/legal-services";

    private final LegalServiceService legalServiceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalService create(@Valid @RequestBody LegalService legalService) {
        return this.legalServiceService.create(legalService);
    }
}