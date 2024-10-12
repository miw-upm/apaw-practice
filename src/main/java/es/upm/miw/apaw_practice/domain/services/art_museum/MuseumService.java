package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.MuseumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MuseumService {
    private final MuseumPersistence museumPersistence;

    @Autowired
    public MuseumService(MuseumPersistence museumPersistence) {
        this.museumPersistence = museumPersistence;
    }

    public Museum read(String name) {
        return this.museumPersistence.readByName(name);
    }

    public void delete(String name) {
        this.museumPersistence.deleteByName(name);
    }

    public void updateExhibitionPrice(String museumName, String exhibitionName, BigDecimal price) {
        Museum museum = this.museumPersistence.readByName(museumName);

        Exhibition exhibition = museum.getExhibitions().stream()
                .filter(exhibitionF -> exhibitionF.getName().equals(exhibitionName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Exhibition not found: " + exhibitionName));

        exhibition.setPrice(price);
        this.museumPersistence.updateExhibitionPrice(museum, exhibitionName);
    }

    public BigDecimal findByArtistNameSumPricesExhibitions(String artistName) {
        return this.museumPersistence.findByArtistNameSumPricesExhibitions(artistName);
    }
}
