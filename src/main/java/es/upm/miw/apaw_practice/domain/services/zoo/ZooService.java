package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
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
}