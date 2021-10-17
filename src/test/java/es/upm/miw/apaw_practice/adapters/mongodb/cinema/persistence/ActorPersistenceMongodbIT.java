package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


@TestConfig
public class ActorPersistenceMongodbIT {
    @Autowired
    private ActorPersistenceMongodb actorPersistence;

    @Test
    void testCreate() {
        Actor actor =
                new Actor("Elisabeth", "Banks", 47);
        assertEquals(actor.getName(), this.actorPersistence.create(actor).getName());
        assertEquals(actor.getFamilyName(), this.actorPersistence.create(actor).getFamilyName());
        assertEquals(actor.getAge(), this.actorPersistence.create(actor).getAge());
    }

}
