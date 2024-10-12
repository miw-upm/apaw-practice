package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.MuseumPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class MuseumServiceIT {
    @Autowired
    private MuseumService museumService;

    @Autowired
    private MuseumPersistence museumPersistence;

    @Test
    void testUpdateExhibitionPrice() {
        String museumName = "Thyssen";
        String exhibitionName = "Century 15th";
        BigDecimal newPrice = BigDecimal.valueOf(35.00);

        museumService.updateExhibitionPrice(museumName, exhibitionName, newPrice);
        Museum updatedMuseum = museumPersistence.readByName(museumName);

        Exhibition updatedExhibition = updatedMuseum.getExhibitions().stream()
                .filter(exhibition -> exhibition.getName().equals(exhibitionName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Exhibition not found: " + exhibitionName));

        assertEquals(newPrice, updatedExhibition.getPrice());
    }
}
