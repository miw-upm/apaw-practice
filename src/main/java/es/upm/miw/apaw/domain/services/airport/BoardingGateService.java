package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.persistenceports.airport.BoardingGatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardingGateService {

    private final BoardingGatePersistence boardingGatePersistence;

    @Autowired
    public BoardingGateService(BoardingGatePersistence boardingGatePersistence) {
        this.boardingGatePersistence = boardingGatePersistence;
    }
}
