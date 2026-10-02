package es.upm.miw.apaw.adapters.in.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.services.immigrationissues.LawBasisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(LawBasisResource.LAW_BASES)
@RequiredArgsConstructor
public class LawBasisResource {

    public static final String LAW_BASES = "/law-bases";
    public static final String ID = "/{id}";

    private final LawBasisService lawBasisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LawBasis create(@Valid @RequestBody LawBasis lawBasis) {
        return this.lawBasisService.create(lawBasis);
    }

    @GetMapping(ID)
    public LawBasis read(@PathVariable UUID id) {
        return this.lawBasisService.read(id);
    }

    @PutMapping(ID)
    public LawBasis update(@PathVariable UUID id, @Valid @RequestBody LawBasis lawBasis) {
        return this.lawBasisService.update(id, lawBasis);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.lawBasisService.delete(id);
    }

    @GetMapping
    public List<LawBasis> findAll() {
        return this.lawBasisService.findAll();
    }
}