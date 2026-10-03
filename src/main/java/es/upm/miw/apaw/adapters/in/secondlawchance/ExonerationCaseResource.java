package es.upm.miw.apaw.adapters.in.secondlawchance;

import es.upm.miw.apaw.domain.model.secondlawchance.CreationExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.services.secondlawchance.ExonerationCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExonerationCaseResource.EXONERATION_CASES)
@RequiredArgsConstructor
public class ExonerationCaseResource {
    public static final String EXONERATION_CASES = "/exoneration-cases";

    private final ExonerationCaseService exonerationCaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExonerationCase create(@Valid @RequestBody CreationExonerationCase creation) {
        return this.exonerationCaseService.create(creation);
    }
}
