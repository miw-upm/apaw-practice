package es.upm.miw.apaw_practice.domain.services.shopping_center;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center.ProviderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    private final ProviderPersistence providerPersistence;

    @Autowired
    public ProviderService(ProviderPersistence providerPersistence) {
        this.providerPersistence = providerPersistence;
    }

    public Provider create(Provider provider) {
        this.assertNameNotExist(provider.getName());
        return this.providerPersistence.create(provider);
    }

    public void assertNameNotExist(String name) {
        if (this.providerPersistence.existName(name)) {
            throw new ConflictException("Name exist: " + name);
        }
    }
}
