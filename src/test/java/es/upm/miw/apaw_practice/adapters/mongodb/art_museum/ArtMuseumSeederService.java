package es.upm.miw.apaw_practice.adapters.mongodb.art_museum;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.ArtworkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.MuseumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtworkEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ExhibitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.MuseumEntity;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ArtMuseumSeederService {
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private MuseumRepository museumRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Art Museum Initial Load -----------");

        ArtworkEntity[] artworks = {
                new ArtworkEntity(new Artwork("27001", "La Gioconda", 1506)),
                new ArtworkEntity(new Artwork("27002", "The Starry Night", 1889)),
                new ArtworkEntity(new Artwork("27003", "Vase with Fifteen Sunflowers", 1888)),
                new ArtworkEntity(new Artwork("27004", "Las Meninas", 1656)),
                new ArtworkEntity(new Artwork("27005", "Guernica", 1937)),
                new ArtworkEntity(new Artwork("27006", "The Last Supper", 1498)),
        };
        this.artworkRepository.saveAll(Arrays.asList(artworks));

        ArtistEntity[] artists = {
                new ArtistEntity("Leonardo da Vinci", 67, "Renaissance", List.of(artworks[0], artworks[5])),
                new ArtistEntity("Vincent van Gogh", 37, "Post-Impressionism", List.of(artworks[1], artworks[2])),
                new ArtistEntity("Pablo Picasso", 91, "Cubism", List.of(artworks[4])),
                new ArtistEntity("Diego Velázquez", 61, "Baroque", List.of(artworks[3]))
        };
        this.artistRepository.saveAll(Arrays.asList(artists));

        ExhibitionEntity[] exhibitions = {
                new ExhibitionEntity("Spanish authors", LocalDateTime.of(2024, 10, 10,10, 0), BigDecimal.valueOf(35.00), List.of(artworks[3], artworks[4])),
                new ExhibitionEntity("Impressionist authors", LocalDateTime.of(2024, 12, 12, 10, 0), BigDecimal.valueOf(40.00), List.of(artworks[1], artworks[2])),
                new ExhibitionEntity("Century 15th", LocalDateTime.of(2025, 5, 20, 11, 0), BigDecimal.valueOf(15.00), List.of(artworks[5]))
        };
        MuseumEntity[] museums = {
                new MuseumEntity("El Prado", 600, true, exhibitions[0]),
                new MuseumEntity("Thyssen", 300, false, exhibitions[1]),
                new MuseumEntity("El Prado", 600, true, exhibitions[2])
        };
        this.museumRepository.saveAll(Arrays.asList(museums));
    }

    public void deleteAll() {
        this.artworkRepository.deleteAll();
        this.artistRepository.deleteAll();
        this.museumRepository.deleteAll();
    }
}
