package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.CourtRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.EquipmentEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.CourtPersistence;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository("courtPersistence")
public class CourtPersistenceMongoDB implements CourtPersistence {

    private CourtRepository courtRepository;

    @Override
    public Stream<BigDecimal> getEquipmentValues(int number) {
        return this.courtRepository.findByNumber(number)
                .getReservations().stream()
                .flatMap(reservation -> reservation.getPlayers().stream()
                        .flatMap(player -> player.getEquipmentList().stream()))
                .map(EquipmentEntity::getTotalPrice);
    }
}
