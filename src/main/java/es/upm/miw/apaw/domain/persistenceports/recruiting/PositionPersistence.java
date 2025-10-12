package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionPersistence {

    List<Position> readAll();

    Position read(Integer reference);

    void update(Integer reference, Position position);

    Position create(Position position);
}