package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.PiecePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class PieceResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PiecePersistence piecePersistence;

    @Autowired
    private ManufacturerPersistence manufacturerPersistence;

    @Test
    void testCreateWithExistingPartNumber() {
        assertTrue(piecePersistence.existPartNumber("WSDF"));
        Optional<Manufacturer> manuFacturer = manufacturerPersistence.readAll().filter(manufacturer -> "Tesla".equals(manufacturer.getName())).findFirst();
        Piece piece = new Piece("WSDF", "Engine", new BigDecimal(200), List.of(manuFacturer.get()));
        createPiece(piece).expectStatus().is4xxClientError();
    }


    private WebTestClient.ResponseSpec createPiece(Piece piece) {
        return webTestClient
                .post()
                .uri(PieceResource.PIECES)
                .body(BodyInserters.fromValue(piece))
                .exchange();
    }

    @Test
    void testCreate(){
        Optional<Manufacturer> manuFacturer = manufacturerPersistence.readAll().filter(manufacturer -> "Tesla".equals(manufacturer.getName())).findFirst();
        Piece piece = new Piece("HIMY","Tire", new BigDecimal(150), List.of(manuFacturer.get()));
        createPiece(piece).expectStatus().isCreated();
        assertTrue(piecePersistence.existPartNumber(piece.getPartNumber()));
    }
}
