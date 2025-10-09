package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.BoardingGateRepository;
import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import es.upm.miw.apaw.domain.persistenceports.airport.BoardingGatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardingGatePersistence")
public class BoardingGatePersistenceMongodb implements BoardingGatePersistence {

    private final BoardingGateRepository boardingGateRepository;

    @Autowired
    public BoardingGatePersistenceMongodb(BoardingGateRepository boardingGateRepository) {
        this.boardingGateRepository = boardingGateRepository;
    }

    @Override
    public BoardingGate update(BoardingGate boardingGate) {
        return null;
    }
}
