package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtworkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtworkEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtworkPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("artworkPersistence")
public class ArtworkPersistenceMongodb implements ArtworkPersistence {
    private final ArtworkRepository artworkRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtworkPersistenceMongodb(ArtworkRepository artworkRepository, ArtistRepository artistRepository) {
        this.artworkRepository = artworkRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public Artwork readByInventoryNumber(String inventoryNumber) {
        return this.artworkRepository
                .findByInventoryNumber(inventoryNumber)
                .orElseThrow(() -> new NotFoundException("Artwork inventory number: " + inventoryNumber))
                .toArtwork();
    }

    @Override
    public Artwork update(Artwork artwork) {
        ArtworkEntity artworkEntity = this.artworkRepository
                .findByInventoryNumber(artwork.getInventoryNumber())
                .orElseThrow(() -> new NotFoundException("Artwork inventory number: " + artwork.getInventoryNumber()));
        ArtistEntity artistEntity = this.artistRepository
                .findByArtistName(artwork.getArtist().getArtistName())
                .orElseGet(() -> {
                    ArtistEntity newArtist = new ArtistEntity(artwork.getArtist());
                    return this.artistRepository.save(newArtist);
                });

        artworkEntity.setTitleName(artwork.getTitleName());
        artworkEntity.setYear(artwork.getYear());
        artworkEntity.setArtist(artistEntity);
        return this.artworkRepository.save(artworkEntity).toArtwork();
    }
}
