package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.hotel_retired.BookingResource;
import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import es.upm.miw.apaw_practice.domain.services.martial_art.MartialArtsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MartialArtClassResource.MARTIALARTSCLASS)
public class MartialArtClassResource {
    static final String MARTIALARTSCLASS = "/martial_art/martialartsclass";

    private final MartialArtsClassService martialArtsClassService;

    @Autowired
    public MartialArtClassResource(MartialArtsClassService martialArtsClassService) {
        this.martialArtsClassService = martialArtsClassService;
    }

    @PostMapping
    public MartialArtsClass create(@RequestBody MartialArtsClass martialArtsClass) {
        return this.martialArtsClassService.create(martialArtsClass);
    }
}
