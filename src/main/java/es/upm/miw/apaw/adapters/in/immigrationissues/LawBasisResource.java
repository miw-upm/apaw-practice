package es.upm.miw.apaw.adapters.in.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.services.immigrationissues.LawBasisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LawBasisResource.LAW_BASES)
@RequiredArgsConstructor
public class LawBasisResource {

    public static final String LAW_BASES = "/law-bases";

    private final LawBasisService lawBasisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LawBasis create(@Valid @RequestBody LawBasis lawBasis) {
        return this.lawBasisService.create(lawBasis);
    }
}