package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtworkPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtworkService {
    private final ArtworkPersistence artworkPersistence;

    @Autowired
    public ArtworkService(ArtworkPersistence artworkPersistence) {
        this.artworkPersistence = artworkPersistence;
    }

    public Artwork update(String inventoryNumber, Artwork artwork) {
        Artwork existingArtwork = this.artworkPersistence.readByInventoryNumber(inventoryNumber);

        existingArtwork.setTitleName(artwork.getTitleName());
        existingArtwork.setYear(artwork.getYear());
        existingArtwork.setArtist(artwork.getArtist());
        return this.artworkPersistence.update(existingArtwork);
    }

}
