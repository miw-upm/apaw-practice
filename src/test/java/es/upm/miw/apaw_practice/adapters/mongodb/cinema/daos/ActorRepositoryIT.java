package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ActorRepositoryIT {
    @Autowired
    private ActorRepository actorRepository;
    
    @Test
    void testFindActorName() {
        List<ActorEntity> actor = this.actorRepository.findAll();

        assertEquals(actor.get(0).getName(), "Jennifer");
    }
}
