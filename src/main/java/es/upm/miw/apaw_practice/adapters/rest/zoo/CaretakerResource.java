package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import es.upm.miw.apaw_practice.domain.services.zoo.CaretakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(CaretakerResource.CARETAKERS)
public class CaretakerResource {

    static final String CARETAKERS = "/zoo/caretakers";
    static final String SEARCH = "/search";

    private final CaretakerService caretakerService;

    @Autowired
    public CaretakerResource(CaretakerService caretakerService) {
        this.caretakerService = caretakerService;
    }

    @GetMapping(SEARCH)
    public Caretaker findByDni(@RequestParam String dni) {
        return this.caretakerService.findByDni(dni);
    }
}
