package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.services.pharmacy.DispensingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DispensingResource.DISPENSINGS)
public class DispensingResource {
    static final String DISPENSINGS = "/pharmacy/dispensings";

    static final String ID_ID = "/{id}";

    private final DispensingService dispensingService;

    @Autowired
    public DispensingResource(DispensingService dispensingService) {
        this.dispensingService = dispensingService;
    }

    @PutMapping
    public Dispensing updateDispensing(@RequestBody Dispensing dispensing) {
        return this.dispensingService.updateDispensing(dispensing);
    }

    @DeleteMapping(ID_ID)
    public void deleteDispensing(@PathVariable String id) {
        this.dispensingService.deleteDispensing(id);
    }
}
