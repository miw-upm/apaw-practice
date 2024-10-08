package es.upm.miw.apaw_practice.domain.persistence_ports.car;

import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.car.Piece;

@Repository
public interface PiecePersistence {

    Piece create(Piece piece);

    Piece readByPartNumber(String partNumber);

    boolean existPartNumber(String partNumber);
}
