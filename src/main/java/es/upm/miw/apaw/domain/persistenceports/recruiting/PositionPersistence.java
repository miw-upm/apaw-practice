package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionPersistence {

    Position create(Position position);

    Position read(Integer reference);

    void update(Integer reference, Position position);
}
