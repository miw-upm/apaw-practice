package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CageService {

    private final CagePersistence cagePersistence;

    @Autowired
    public CageService(CagePersistence cagePersistence) {
        this.cagePersistence = cagePersistence;
    }

    public Stream<ZooAddress> findZooAddressesByCageLocationCode(String locationCode) {
        return this.cagePersistence.findZooAddressesByCageLocationCode(locationCode);
    }
}
