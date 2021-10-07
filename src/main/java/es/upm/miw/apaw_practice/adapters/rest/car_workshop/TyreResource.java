package es.upm.miw.apaw_practice.adapters.rest.car_workshop;


import es.upm.miw.apaw_practice.domain.services.car_workshop.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TyreResource.TYRES)
public class TyreResource {

    static final String TYRES = "/car-workshop/tyres";
    static final String MANUFACTURER = "/manufacturer";

    private final TyreService tyreService;

    @Autowired
    public TyreResource(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    @DeleteMapping(TyreResource.MANUFACTURER)
    public Integer deleteManufacturer(@PathVariable String manufacturer) {
        return this.tyreService.deleteManufacturer(String manufacturer);
    }
}
