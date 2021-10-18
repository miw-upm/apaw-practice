package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationEntityTest {

    private List<PlayerEntity> playerEntityList;

    @BeforeEach
    void beforeEach(){
        this.playerEntityList = new ArrayList<>();
        this.playerEntityList.add(new PlayerEntity("0P", "Javier", "Gil", 25, List.of()));
        this.playerEntityList.add(new PlayerEntity("1P", "Pedro", "Aguado", 55, List.of()));
        this.playerEntityList.add(new PlayerEntity("2P", "Rosa", "Martos", 32, List.of()));
    }

    @Test
    void testToPlayerList(){
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("0P", "Javier", "Gil", 25));
        playerList.add(new Player("1P", "Pedro", "Aguado", 55));
        playerList.add(new Player("2P", "Rosa", "Martos", 32));

        assertEquals(playerList.get(0), ReservationEntity.toPlayerList(playerEntityList).get(0));
        assertEquals(playerList.get(1), ReservationEntity.toPlayerList(playerEntityList).get(1));
        assertEquals(playerList.get(2), ReservationEntity.toPlayerList(playerEntityList).get(2));
    }

    @Test
    void testToReservation(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ReservationEntity reservationEntity = new ReservationEntity("Javier", localDateTime, 2, this.playerEntityList);
        assertEquals(reservationEntity.getOwnerName(), reservationEntity.toReservation().getOwnerName());
        assertEquals(reservationEntity.getPlayers().get(0).getDni(), reservationEntity.toReservation().getPlayers().get(0).getDNI());
    }
}
