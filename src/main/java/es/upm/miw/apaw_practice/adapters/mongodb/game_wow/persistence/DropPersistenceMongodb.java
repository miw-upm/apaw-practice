package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.Droprepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.DropEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.DropPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("dropPersistence")
public class DropPersistenceMongodb implements DropPersistence {

    private final Droprepository droprepository;

    @Autowired
    public DropPersistenceMongodb(Droprepository droprepository) {
        this.droprepository = droprepository;
    }

    @Override
    public Stream<Drop> readAll() {
        return this.droprepository.findAll().stream()
                .map(DropEntity::todrop);
    }
}
