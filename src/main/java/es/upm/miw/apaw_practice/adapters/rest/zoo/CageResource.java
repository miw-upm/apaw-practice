package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import es.upm.miw.apaw_practice.domain.services.zoo.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(CageResource.CAGES)
public class CageResource {

    static final String CAGES = "/zoo/cages";
    static final String LOCATION_CODE = "/{locationCode}";
    static final String ZOOS_ADDRESSES = "/zoos/addresses";

    private final CageService cageService;

    @Autowired
    public CageResource(CageService cageService) {
        this.cageService = cageService;
    }

    @GetMapping(LOCATION_CODE + ZOOS_ADDRESSES)
    public Stream<ZooAddress> findZooAddressesByCageLocationCode(@PathVariable String locationCode) {
        return this.cageService.findZooAddressesByCageLocationCode(locationCode);
    }
}
