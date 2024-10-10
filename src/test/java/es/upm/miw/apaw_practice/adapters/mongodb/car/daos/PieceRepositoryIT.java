package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;


import es.upm.miw.apaw_practice.TestConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PieceRepositoryIT {

    @Autowired
    private PieceRepository pieceRepository;

    @Test
    void testFindByPartNumber() {


        Optional<PieceEntity> pieceEntity = pieceRepository.findByPartNumber("WSDF");
        assertTrue(pieceEntity.isPresent());
        assertFalse(pieceEntity.isEmpty());
        assertNotNull(pieceEntity.get());
        Piece piece = pieceEntity.get().toPiece();
        assertEquals("WSDF", piece.getPartNumber());
        assertEquals("Engine", piece.getDescription());
        assertEquals(new BigDecimal(200), piece.getCost());
    }

    void testFindParNumberNotExisting(){
        assertTrue(this.pieceRepository.findByPartNumber("TTQE").isEmpty());
    }
}
