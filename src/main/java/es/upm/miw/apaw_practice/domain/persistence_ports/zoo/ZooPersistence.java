package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Cage;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;

@Repository
public interface ZooPersistence {

    void create(Zoo zoo);

    Zoo findById(String id);

    void updateZipCode(String id, String zipCode);

    void updateNextFumigation(String id, CageFumigation cageFumigation);
}
