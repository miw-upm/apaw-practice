package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.services.art_museum.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MuseumResource.MUSEUMS)
public class MuseumResource {
    static final String MUSEUMS = "/art-museum/museums";
    static final String NAME_MUSEUM = "/{name}";

    private final MuseumService museumService;

    @Autowired
    public MuseumResource(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping(NAME_MUSEUM)
    public Museum read(@PathVariable String name) {
        return Museum.ofArtworkInventoryNumber(this.museumService.read(name));
    }
}
