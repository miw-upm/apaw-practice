package es.upm.miw.apaw.adapters.in.probate;

import es.upm.miw.apaw.domain.model.probate.CreationEstate;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;
import es.upm.miw.apaw.domain.services.probate.EstateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EstateResource.ESTATES)
@RequiredArgsConstructor
public class EstateResource {
    public static final String ESTATES = "/estates";
    public static final String REPORT = "/report";

    private final EstateService estateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estate create(@Valid @RequestBody CreationEstate creation) {
        return this.estateService.create(creation);
    }

    @GetMapping(REPORT)
    public List<EstateUsageReport> findUsageReport() {
        return this.estateService.findUsageReport();
    }
}
