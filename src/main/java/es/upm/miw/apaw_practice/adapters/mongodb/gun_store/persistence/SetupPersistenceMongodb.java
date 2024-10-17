package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.SetupRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.SetupEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.SetupPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("setupPersistence")
public class SetupPersistenceMongodb implements SetupPersistence {

    private final SetupRepository setupRepository;

    @Autowired
    public SetupPersistenceMongodb(SetupRepository setupRepository) {
        this.setupRepository = setupRepository;
    }

    public void delete(Integer id) {
        this.setupRepository.deleteById(id);
    }

    public Setup read(Integer id) {
        return this.setupRepository.findById(id).orElseThrow(() -> new NotFoundException(" Setup id: " + id))
                .toSetup();
    }

    public Setup create(Setup setup) {
        return this.setupRepository.save(new SetupEntity(setup)).toSetup();
    }

}
