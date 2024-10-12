package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.services.art_museum.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(MuseumResource.MUSEUMS)
public class MuseumResource {
    static final String MUSEUMS = "/art-museum/museums";
    static final String NAME_MUSEUM = "/{museumName}";
    static final String EXHIBITIONS = "/exhibitions";
    static final String NAME_EXHIBITION = "/{exhibitionName}";

    private final MuseumService museumService;

    @Autowired
    public MuseumResource(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping(NAME_MUSEUM)
    public Museum read(@PathVariable String museumName) {
        return Museum.ofArtworkInventoryNumber(this.museumService.read(museumName));
    }

    @DeleteMapping(NAME_MUSEUM)
    public void delete(@PathVariable String museumName) {
        this.museumService.delete(museumName);
    }

    @PatchMapping(NAME_MUSEUM + EXHIBITIONS + NAME_EXHIBITION)
    public void updateExhibitionPrice(@PathVariable String museumName, @PathVariable String exhibitionName,
                                      @RequestBody BigDecimal price) {
        this.museumService.updateExhibitionPrice(museumName, exhibitionName, price);
    }
}
