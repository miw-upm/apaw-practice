package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.services.euregulation.EURegulationService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(EURegulationResource.EU_REGULATIONS)
@RequiredArgsConstructor
public class EURegulationResource {
    public static final String EU_REGULATIONS = "/eu-regulations";
    public static final String ID = "/{id}";

    private final EURegulationService euRegulationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EURegulation create(@Valid @RequestBody EURegulationCreationDto creation) {
        return this.euRegulationService.create(creation.toDomain());
    }

    @GetMapping
    public List<EURegulation> findAll() {
        return this.euRegulationService.findAll();
    }

    @GetMapping(ID)
    public EURegulation read(@PathVariable UUID id) {
        return this.euRegulationService.read(id);
    }

    @PutMapping(ID)
    public EURegulation update(@Valid @PathVariable UUID id, @Valid @RequestBody EURegulationCreationDto update) {
        return this.euRegulationService.update(id, update.toDomain());
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.euRegulationService.delete(id);
    }
}
