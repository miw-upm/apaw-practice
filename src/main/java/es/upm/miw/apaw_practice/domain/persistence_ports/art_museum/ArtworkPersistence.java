package es.upm.miw.apaw_practice.domain.persistence_ports.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkPersistence {
    Artwork readByInventoryNumber(String inventoryNumber);

    Artwork update(Artwork artwork);

}
