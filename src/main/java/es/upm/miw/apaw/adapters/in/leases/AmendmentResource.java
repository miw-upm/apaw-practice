package es.upm.miw.apaw.adapters.in.leases;

import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentUpdate;
import es.upm.miw.apaw.domain.services.leases.AmendmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AmendmentResource.AMENDMENTS)
@RequiredArgsConstructor
public class AmendmentResource {
    public static final String AMENDMENTS = "/amendments";
    public static final String ID = "/{id}";

    private final AmendmentService amendmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Amendment create(@Valid @RequestBody Amendment amendment) {
        return this.amendmentService.create(amendment);
    }

    @GetMapping
    public List<Amendment> findAll() {
        return this.amendmentService.findAll();
    }

    @GetMapping(ID)
    public Amendment read(@PathVariable UUID id) {
        return this.amendmentService.read(id);
    }

    @PutMapping(ID)
    public Amendment update(@PathVariable UUID id, @Valid @RequestBody Amendment amendment) {
        return this.amendmentService.update(id, amendment);
    }

    @PatchMapping(ID)
    public Amendment patch(@PathVariable UUID id, @RequestBody AmendmentUpdate update) {
        return this.amendmentService.patch(id, update);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.amendmentService.delete(id);
    }
}
