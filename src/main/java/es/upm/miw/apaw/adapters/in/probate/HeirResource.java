package es.upm.miw.apaw.adapters.in.probate;

import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.services.probate.HeirService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(HeirResource.HEIRS)
@RequiredArgsConstructor
public class HeirResource {
    public static final String HEIRS = "/heirs";

    private final HeirService heirService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Heir create(@Valid @RequestBody Heir heir) {
        return this.heirService.create(heir);
    }

    @GetMapping
    public List<Heir> findAll() {
        return this.heirService.findAll();
    }
}