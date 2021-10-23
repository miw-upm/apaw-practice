package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationEntityTest {

    private List<PlayerEntity> playerEntityList;

    private ReservationEntity reservationEntity;

    @BeforeEach
    void beforeEach(){
        this.playerEntityList = new ArrayList<>();
        this.playerEntityList.add(new PlayerEntity("0P", "Javier", "Gil", 25, List.of()));
        this.playerEntityList.add(new PlayerEntity("1P", "Pedro", "Aguado", 55, List.of()));
        this.playerEntityList.add(new PlayerEntity("2P", "Rosa", "Martos", 32, List.of()));

        LocalDateTime date = LocalDateTime.of(2021,10,21,12,0);
        this.reservationEntity = new ReservationEntity("Rosa", date, 2, this.playerEntityList);
    }

    @Test
    void testGetPlayersDNIs(){
        List<Player> playerDNIList = this.reservationEntity.getPlayersDNIs();
        assertEquals(this.playerEntityList.size(), playerDNIList.size());
        for (Player player : playerDNIList) {
            assertNull(player.getName());
            assertNull(player.getSurname());
            assertNull(player.getAge());
            assertNull(player.getEquipmentList());
            assertNotNull(player.getDNI());
        }
    }

    @Test
    void testSetPlayersFromPlayerList(){
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("9L", "Sara", "Gil", 23));
        playerList.add(new Player("8L", "Juan", "Aguado", 60));
        playerList.add(new Player("7L", "Rosa", "Tejada", 38));
        this.reservationEntity.setPlayersFromPlayerList(playerList);

        List<PlayerEntity> playerEntityList = reservationEntity.getPlayers();
        assertEquals(playerEntityList.size(), playerList.size());
        for (int i = 0; i < playerList.size(); i++) {
            assertEquals(playerEntityList.get(i).getDni(), playerList.get(i).getDNI());
        }
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
