package es.upm.miw.apaw_practice.domain.persistence_ports.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderPersistence {

    Provider create(Provider provider);

    boolean existName(String name);
}
