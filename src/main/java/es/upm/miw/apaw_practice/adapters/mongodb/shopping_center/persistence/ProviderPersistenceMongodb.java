package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.ProviderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ProviderEntity;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ProviderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("providerPersistence")
public class ProviderPersistenceMongodb implements ProviderPersistence {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderPersistenceMongodb(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Provider create(Provider provider) {
        return this.providerRepository
                .save(new ProviderEntity(provider))
                .toProvider();
    }

    @Override
    public boolean existName(String name) {
        return this.providerRepository
                .findByName(name)
                .isPresent();
    }
}
