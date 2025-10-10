package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.BoardingGate;

import java.util.UUID;

public interface BoardingGatePersistence {
    BoardingGate update(BoardingGate boardingGate);
    BoardingGate findById(UUID id);
}
