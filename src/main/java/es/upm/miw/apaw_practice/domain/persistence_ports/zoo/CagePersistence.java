package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.*;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;

@Repository
public interface CagePersistence {

    void updateNextFumigation(Zoo zoo, CageFumigation cageFumigation);

    Stream<Cage> findAllContainingAny(Animal animal);

    Stream<ZooAddress> findZooAddressesByCageLocationCode(String locationCode);
}
