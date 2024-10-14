package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.bson.assertions.Assertions.assertNotNull;

@TestConfig
class PieceServiceIT {

    @Autowired
    ManufacturerPersistence manufacturerPersistence;

    @Autowired
    PieceService pieceService;

    @Test
    void testCreatePiece() {
        Optional<Manufacturer> manuFacturer = manufacturerPersistence.readAll().filter(manufacturer -> "Tesla".equals(manufacturer.getName())).findFirst();
        Piece piece = new Piece("UUTY", "Battery", new BigDecimal(290.7), List.of(manuFacturer.get()));


        Piece createdPiece = this.pieceService.create(piece);
        assertNotNull(createdPiece);
    }
}
