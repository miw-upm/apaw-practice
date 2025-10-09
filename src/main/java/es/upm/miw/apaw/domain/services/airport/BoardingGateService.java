package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import es.upm.miw.apaw.domain.persistenceports.airport.BoardingGatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BoardingGateService {

    private final BoardingGatePersistence boardingGatePersistence;

    @Autowired
    public BoardingGateService(BoardingGatePersistence boardingGatePersistence) {
        this.boardingGatePersistence = boardingGatePersistence;
    }

    public BoardingGate updateOpened(UUID id, Boolean opened) {
        BoardingGate boardingGate = this.boardingGatePersistence.findById(id);
        boardingGate.setOpened(opened);
        return this.boardingGatePersistence.update(boardingGate);
    }
}
