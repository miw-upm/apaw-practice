package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketBallPersistenceMongodbIT {

    @Autowired
    private BasketBallPersistenceMongodb basketBallPersistenceMongodb;

    @Test
    void testUpdateNotFound() {
        BasketPlayer player = new BasketPlayer("12345678A", "Lebron", 7, 30);
        BasketMatch basketMatch = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", List.of(player));
        BasketBall basketBall =
                new BasketBall(1, "Nike", new BigDecimal("50.0"), basketMatch);
        assertThrows(NotFoundException.class, () -> this.basketBallPersistenceMongodb.update(0, basketBall));
    }

    @Test
    void testUpdate() {
        BasketPlayer player = new BasketPlayer("12345678A", "Lebron", 7, 30);
        BasketMatch basketMatch = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", List.of(player));
        BasketBall basketBall =
                new BasketBall(1, "Nike", new BigDecimal("50.0"), basketMatch);
        BasketBall updatedBasketBall = this.basketBallPersistenceMongodb.update(1, basketBall);
        assertNotNull(updatedBasketBall);
        assertEquals(1,updatedBasketBall.getId());
        assertEquals(new BigDecimal("50.0"), updatedBasketBall.getPrice());
    }

    @Test
    void testGetDistinctBrands(){
        List<String> brands = this.basketBallPersistenceMongodb.getDistinctBrands("ACB", "Lebron");
        assertTrue(brands.contains("Nike"));
        assertTrue(brands.contains("Spalding"));
        assertTrue(brands.contains("Wilson"));
        assertTrue(brands.contains("Peak"));
        assertTrue(brands.contains("New Balance"));
    }

    @Test
    void testGetTotalPrice(){
        BigDecimal sum = this.basketBallPersistenceMongodb.getTotalPrice(15);
        assertEquals(new BigDecimal("127.30"), sum);
    }
}
