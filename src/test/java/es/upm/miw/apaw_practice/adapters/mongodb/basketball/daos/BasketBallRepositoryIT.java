package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketBallEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class BasketBallRepositoryIT {

    @Autowired
    private BasketBallRepository basketBallRepository;

    @Test
    void testFindByBallId() {
        Optional<BasketBallEntity> basketBallEntity = basketBallRepository.findByBallId(1);
        assertTrue(basketBallEntity.isPresent());
        BasketBallEntity basketBall = basketBallEntity.get();
        assertEquals(1, basketBall.getBallId());
        assertEquals("Nike", basketBall.getBrand());
        assertEquals(new BigDecimal("50.0"), basketBall.getPrice());
    }
}
