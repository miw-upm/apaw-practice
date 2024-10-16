package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.GunRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.GunPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("gunPersistence")
public class GunPersistenceMongodb implements GunPersistence {

    private GunRepository gunRepository;

    @Autowired
    public GunPersistenceMongodb(GunRepository gunRepository) {
        this.gunRepository = gunRepository;
    }

    public Gun readByName(String name) {
        return this.gunRepository.findByName(name).orElseThrow(() -> new NotFoundException(" Gun name: " + name))
                .toGun();
    }

}
