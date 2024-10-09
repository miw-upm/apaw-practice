package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ReservationEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("ReservationPersistence")
public class ReservationPersistenceMongodb implements ReservationPersistence {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationPersistenceMongodb(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
