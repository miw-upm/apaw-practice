package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.services.emarketer.EmarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EmarketerResource.EMARKETER)
public class EmarketerResource {

    static final String EMARKETER = "/emarketer/emarketer";

    static final String NAME = "/{name}";

    private final EmarketerService emarketerService;

    @Autowired
    public EmarketerResource(EmarketerService emarketerService) {
        this.emarketerService = emarketerService;
    }

    @DeleteMapping(EmarketerResource.NAME)
    public void delete(@PathVariable String name) {
        this.emarketerService.delete(name);
    }

}
