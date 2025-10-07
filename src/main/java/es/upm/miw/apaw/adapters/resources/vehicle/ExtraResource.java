package es.upm.miw.apaw.adapters.resources.vehicle;

import es.upm.miw.apaw.domain.services.vehicle.ExtraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ExtraResource.EXTRAS)
public class ExtraResource {
    public static final String EXTRAS = "/vehicle/extras";
    public static final String EXTRA_ID = "/{id}";

    private final ExtraService extraService;

    @Autowired
    public ExtraResource(ExtraService extraService) {
        this.extraService = extraService;
    }

    @DeleteMapping(EXTRA_ID)
    public void delete(@Valid @PathVariable UUID id) {
        this.extraService.delete(id);
    }
}
