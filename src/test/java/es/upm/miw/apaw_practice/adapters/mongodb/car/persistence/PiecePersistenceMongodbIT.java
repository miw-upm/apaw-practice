package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PiecePersistenceMongodbIT {

    @Autowired
    private PiecePersistenceMongodb piecePersistence;

    @Test
    public void testReadByPartNumber(){
        Piece piece = this.piecePersistence.readByPartNumber("WSDF");
        assertEquals("WSDF", piece.getPartNumber());
        assertEquals("Engine", piece.getDescription());
        assertEquals(new BigDecimal(200), piece.getCost());
        assertTrue(piece.getManufacturerList()
                .stream()
                .map(Manufacturer::getName)
                .collect(Collectors.toList())
                .contains("Tesla"));

    }
}
