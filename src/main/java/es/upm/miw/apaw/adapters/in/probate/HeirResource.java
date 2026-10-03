package es.upm.miw.apaw.adapters.in.probate;

import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.services.probate.HeirService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HeirResource.HEIRS)
@RequiredArgsConstructor
public class HeirResource {
    public static final String HEIRS = "/heirs";

    private final HeirService heirService;

    @PostMapping
    public Heir create(@Valid @RequestBody Heir heir) {
        return this.heirService.create(heir);
    }
}