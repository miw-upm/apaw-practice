package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ActorPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ActorServiceTest {

    @Autowired
    private ActorService actorService;
    private ActorPersistence actorPersistence;

    @Test
    void testCreate () {
        Actor actor= new Actor("Elisabeth", "Banks", 47);
        Actor actorInService = actorService.create(actor);
        assertEquals(actor.getName(), actorInService.getName());
        assertEquals(actor.getFamilyName(), actorInService.getFamilyName());
        assertEquals(actor.getAge(), actorInService.getAge());
    }

    @Test
    void testGetSpectatorsNamesByAge() {
        List<String> spectatorsNames = actorService.getSpectatorsNamesByAge(30);
        assertEquals(1, spectatorsNames.size());
    }
}
