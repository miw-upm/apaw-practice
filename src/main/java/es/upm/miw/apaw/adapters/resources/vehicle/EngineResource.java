package es.upm.miw.apaw.adapters.resources.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Engine;
import es.upm.miw.apaw.domain.services.vehicle.EngineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EngineResource.ENGINES)
public class EngineResource {
    public static final String ENGINES = "/vehicle/engines";
    public static final String CODE_ENGINE_ID = "/{codeEngine}";

    private final EngineService engineService;

    @Autowired
    public EngineResource(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping
    public Engine create(@Valid @RequestBody Engine engine) {
        return this.engineService.create(engine);
    }

    @PutMapping(CODE_ENGINE_ID)
    public Engine update(@Valid @PathVariable String codeEngine, @Valid @RequestBody Engine engine) {
        return this.engineService.update(codeEngine, engine);
    }
}
