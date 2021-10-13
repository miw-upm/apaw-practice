package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.services.car_hire.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ModelResource.MODELS)
public class ModelResource {
    static final String MODELS = "/car-hire/models";

    static final String SEARCH = "/search";

    private final ModelService modelService;

    @Autowired
    public ModelResource(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(ModelResource.SEARCH)
    public Model findModelByVehicleVinNumber(@RequestParam String q) {
        String vinNumber = new LexicalAnalyzer().extractWithAssure(q, "VIN_Number:");
        return this.modelService.findModelByVehicleVinNumber(vinNumber);
    }
}