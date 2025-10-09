package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.BoardingGate;

public interface BoardingGatePersistence {
    BoardingGate update(BoardingGate boardingGate);
}
