package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationEntityTest {

    private List<Player> playerList;

    @BeforeEach
    void beforeEach(){
        this.playerList = new ArrayList<>();
        this.playerList.add(new Player("0P", "Javier", "Gil", 25));
        this.playerList.add(new Player("1P", "Pedro", "Aguado", 55));
        this.playerList.add(new Player("2P", "Rosa", "Martos", 32));
    }

    @Test
    void testToPlayerList(){
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        playerEntityList.add(new PlayerEntity("0P", "Javier", "Gil", 25, List.of()));
        playerEntityList.add(new PlayerEntity("1P", "Pedro", "Aguado", 55, List.of()));
        playerEntityList.add(new PlayerEntity("2P", "Rosa", "Martos", 32, List.of()));

        assertEquals(playerList.get(0), ReservationEntity.toPlayerList(playerEntityList).get(0));
        assertEquals(playerList.get(1), ReservationEntity.toPlayerList(playerEntityList).get(1));
        assertEquals(playerList.get(2), ReservationEntity.toPlayerList(playerEntityList).get(2));
    }
}
