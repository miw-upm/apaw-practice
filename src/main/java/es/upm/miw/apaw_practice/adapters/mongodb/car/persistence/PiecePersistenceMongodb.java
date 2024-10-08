package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.PieceRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.PiecePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car.Piece;

@Repository("piecePersistence")
public class PiecePersistenceMongodb implements PiecePersistence {

    private PieceRepository pieceRepository;

    @Autowired
    public PiecePersistenceMongodb(PieceRepository pieceRepository){
        this.pieceRepository = pieceRepository;
    }

    @Override
    public Piece readByPartNumber(String partNumber){
        return pieceRepository
                .findByPartNumber(partNumber)
                .orElseThrow(() -> new NotFoundException("Piece partNumber: " + partNumber))
                .toPiece();
    }
}
