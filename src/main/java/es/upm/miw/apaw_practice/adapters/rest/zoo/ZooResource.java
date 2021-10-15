package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import es.upm.miw.apaw_practice.domain.services.zoo.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ZooResource.ZOOS)
public class ZooResource {

    static final String ZOOS = "/zoo/zoos";
    static final String ID = "/{id}";
    static final String ZIPCODE = "/address/zipcode";
    static final String CAGES = "/cages";

    private final ZooService zooService;

    @Autowired
    public ZooResource(ZooService zooService) {
        this.zooService =zooService;
    }

    @PostMapping
    public void create(@RequestBody Zoo zoo) {
        if (zoo.isNull()) {
            throw new BadRequestException("Phone number and full address of the new zoo are mandatory");
        } else {
            this.zooService.create(zoo);
        }
    }

    @PutMapping(ID + ZIPCODE)
    public void updateZipcode(@PathVariable("id") String id, @RequestBody ZooAddress address) {
        if (address.getZipCode() == null) {
            throw new BadRequestException("For this update, a new valid zipcode is mandatory");
        } else {
            this.zooService.updateZipCode(id, address.getZipCode());
        }
    }

    @GetMapping(ID)
    public Zoo findById(@PathVariable String id) {
        return this.zooService.findById(id);
    }

    @PatchMapping(ID + CAGES)
    public void updateNextFumigation(@PathVariable String id, @RequestBody CageFumigation cageFumigation) {
        if (cageFumigation.getOldFumigation() == null
        || cageFumigation.getNewFumigation() == null) {
            throw new BadRequestException("For this update, input valid fumigation dates in the body");
        } else {
            this.zooService.updateNextFumigation(id, cageFumigation);
        }
    }
}
