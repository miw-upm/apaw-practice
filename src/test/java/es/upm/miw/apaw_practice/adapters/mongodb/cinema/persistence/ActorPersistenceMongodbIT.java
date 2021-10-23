package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


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

    @Test
    void testRead() {
        Actor actor =
                new Actor("Jennifer", "Lawrence", 31);
        Actor actorDB = this.actorPersistence.read(actor);
        assertEquals(actor.getName(), actorDB.getName());
        assertEquals(actor.getFamilyName(), actorDB.getFamilyName());
    }

    @Test
    void testUpdate() {
        Actor actor =
                new Actor("Jennifer", "Lawrence", 32);
        Actor actorDB = this.actorPersistence.update(actor.getAge(), actor);
        assertEquals(actor.getName(), actorDB.getName());
        assertEquals(actor.getFamilyName(), actorDB.getFamilyName());
    }

    @Test
    void testGetSpectatorsNamesByAge() {
        List<String> nameList = this.actorPersistence.getSpectatorsNamesByAge(30);
        assertEquals(1, nameList.size());
    }
}