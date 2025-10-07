package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.persistenceports.winery.ReservationPersistence;
import es.upm.miw.apaw.domain.persistenceports.winery.TastingSessionPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationPersistence reservationPersistence;
    private final TastingSessionPersistence tastingSessionPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public ReservationService(ReservationPersistence reservationPersistence, UserRestClient userRestClient, TastingSessionPersistence tastingSessionPersistence) {
        this.reservationPersistence = reservationPersistence;
        this.userRestClient = userRestClient;
        this.tastingSessionPersistence = tastingSessionPersistence;
    }

    public Reservation create(Reservation reservation) {
        reservation.setId(UUID.randomUUID());
        reservation.setBookingDate(LocalDateTime.now());
        UserDto userDto = this.userRestClient.readById(reservation.getUser().getId());
        reservation.setUser(userDto);
        TastingSession tastingSession = this.tastingSessionPersistence.readById(reservation.getTastingSession().getId());
        reservation.setTastingSession(tastingSession);
        Reservation reservationDb = this.reservationPersistence.create(reservation);
        reservationDb.setUser(userDto);
        return reservationDb;
    }
}
