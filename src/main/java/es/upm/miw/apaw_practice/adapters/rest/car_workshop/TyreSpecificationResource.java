package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecsModification;
import es.upm.miw.apaw_practice.domain.services.car_workshop.TyreSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TyreSpecificationResource.TYRE_SPECIFICATIONS)
public class TyreSpecificationResource {

    static final String TYRE_SPECIFICATIONS = "/car-workshop/tyre-specifications";
    static final String LOAD_SPEED_INDEX = "/loadSpeedIndex";

    private final TyreSpecificationService tyreSpecificationService;

    @Autowired
    public TyreSpecificationResource(TyreSpecificationService tyreSpecificationService){
        this.tyreSpecificationService = tyreSpecificationService;
    }

    @PatchMapping(LOAD_SPEED_INDEX)
    public void updateLoadSpeedIndex(@RequestBody TyreSpecsModification tyreSpecsModification){
        if(tyreSpecsModification.getNewSpec() != null &&
            tyreSpecsModification.getOldSpec() != null){
            this.tyreSpecificationService.updateLoadSpeedIndex(tyreSpecsModification);
        } else
        {
            throw new BadRequestException("Insufficient information.");
        };


    }
}
