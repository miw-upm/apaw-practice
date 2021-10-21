package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import es.upm.miw.apaw_practice.domain.services.tennis_courts.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
public class ReservationResource {
    public static final String RESERVATIONS = "/reservations";
    public static final String OWNER_NAME = "/{ownerName}";
    public static final String DATE = "/{stringDate}"; // DD:MM:AA
    public static final String TIME = "/{stringTime}"; // HH:MM

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @DeleteMapping(ReservationResource.OWNER_NAME + ReservationResource.DATE + ReservationResource.TIME)
    public void delete(@PathVariable String ownerName, @PathVariable String stringDate, @PathVariable String stringTime){
        this.reservationService.delete(ownerName, stringDate, stringTime);
    }

    @PatchMapping(ReservationResource.OWNER_NAME + ReservationResource.DATE + ReservationResource.TIME)
    public Stream<Player> updatePlayerList(@PathVariable String ownerName, @PathVariable String stringDate, @PathVariable String stringTime, @RequestBody Player[] players){
        Reservation reservation = new Reservation();
        reservation.setPlayers(List.of(players));
        return this.reservationService.updatePlayerList(ownerName, stringDate, stringTime, reservation);
    }

}
