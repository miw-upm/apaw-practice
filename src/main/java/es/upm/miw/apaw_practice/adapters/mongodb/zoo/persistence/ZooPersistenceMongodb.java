package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.ZooRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.ZooPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("zooPersistence")
public class ZooPersistenceMongodb implements ZooPersistence {

    private final ZooRepository zooRepository;

    @Autowired
    public ZooPersistenceMongodb(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    @Override
    public void create(Zoo zoo) {
        this.zooRepository.save(new ZooEntity(zoo));
    }

    @Override
    public ZooEntity findById(String id) {
        return this.zooRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zoo with id: " + id));
    }

    @Override
    public void update(ZooEntity zooEntity) {
        this.zooRepository.save(zooEntity);
    }
}
