package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.services.art_museum.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArtworkResource.ARTWORKS)
public class ArtworkResource {
    static final String ARTWORKS = "/art-museum/artworks";
    static final String ID_INVENTORY_NUMBER = "/{inventoryNumber}";

    private final ArtworkService artworkService;

    @Autowired
    public ArtworkResource(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PutMapping(ID_INVENTORY_NUMBER)
    public Artwork update(@PathVariable String inventoryNumber, @RequestBody Artwork artwork) {
        return this.artworkService.update(inventoryNumber, artwork);
    }
}
