package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.services.zoo.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ZooResource.ZOOS)
public class ZooResource {

    static final String ZOOS = "/zoo/zoos";

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
}
