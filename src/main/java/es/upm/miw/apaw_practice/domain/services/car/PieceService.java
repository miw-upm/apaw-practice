package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.domain.models.car.Piece;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.PiecePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PieceService {
    private final PiecePersistence piecePersistence;
    @Autowired
    public PieceService(PiecePersistence piecePersistence) {
        this.piecePersistence = piecePersistence;
    }

    public void create(Piece piece) {
        piecePersistence.create(piece);
    }

    public boolean existsPartNumber(String partNumber) {
        return piecePersistence.existPartNumber(partNumber);
    }
}
