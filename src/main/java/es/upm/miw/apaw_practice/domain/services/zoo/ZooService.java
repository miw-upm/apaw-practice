package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.ZooPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZooService {

    private final ZooPersistence zooPersistence;

    @Autowired
    public ZooService(ZooPersistence zooPersistence) {
        this.zooPersistence = zooPersistence;
    }

    public void create(Zoo zoo) {
        this.zooPersistence.create(zoo);
    }

    public void updateZipCode(String id, ZooAddress address) {
        ZooEntity zooEntity = this.zooPersistence.findById(id);
        zooEntity.getAddress().setZipCode(address.getZipCode());
        this.zooPersistence.update(zooEntity);
    }

    public Zoo findById(String id) {
        return this.zooPersistence.findById(id).toZoo();
    }
}
