package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.BoardingGateRepository;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.BoardingGateEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import es.upm.miw.apaw.domain.persistenceports.airport.BoardingGatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Repository("boardingGatePersistence")
public class BoardingGatePersistenceMongodb implements BoardingGatePersistence {

    private final BoardingGateRepository boardingGateRepository;

    @Autowired
    public BoardingGatePersistenceMongodb(BoardingGateRepository boardingGateRepository) {
        this.boardingGateRepository = boardingGateRepository;
    }

    @Override
    public BoardingGate update(BoardingGate boardingGate) {
        BoardingGateEntity boardingGateEntity = this.boardingGateRepository
                .findById(boardingGate.getId())
                .orElseThrow(() -> new NotFoundException("BoardingGate id: " + boardingGate.getId()));
        boardingGateEntity.setOpened(boardingGate.getOpened());
        return this.boardingGateRepository.save(boardingGateEntity).toBoardingGate();
    }

    @Override
    public BoardingGate findById(UUID id) {
        return this.boardingGateRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Boarding Gate id: " + id))
                .toBoardingGate();
    }
}
