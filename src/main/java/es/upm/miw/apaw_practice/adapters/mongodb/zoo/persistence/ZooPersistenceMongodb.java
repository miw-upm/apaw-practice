package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.ZooRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CagePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.ZooPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("zooPersistence")
public class ZooPersistenceMongodb implements ZooPersistence {

    private final ZooRepository zooRepository;
    private final CagePersistence cagePersistence;

    @Autowired
    public ZooPersistenceMongodb(ZooRepository zooRepository, CagePersistence cagePersistence) {
        this.zooRepository = zooRepository;
        this.cagePersistence = cagePersistence;
    }

    @Override
    public void create(Zoo zoo) {
        this.zooRepository.save(new ZooEntity(zoo));
    }

    @Override
    public Zoo findById(String id) {
        return this.zooRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zoo with id: " + id))
                .toZoo();
    }

    @Override
    public void updateZipCode(String id, String zipCode) {
        ZooEntity zoo = this.zooRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zoo with id: " + id));
        zoo.getAddress().setZipCode(zipCode);
        this.zooRepository.save(zoo);
    }

    @Override
    public void updateNextFumigation(String id, CageFumigation cageFumigation) {
        Zoo zoo = this.zooRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zoo with id: " + id))
                .toZoo();
        this.cagePersistence.updateNextFumigation(zoo, cageFumigation);
    }
}
