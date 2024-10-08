package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.SoldierPersistence;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SoldierService {
    private final SoldierPersistence soldierPersistence;

    public SoldierService(SoldierPersistence soldierPersistence) {
        this.soldierPersistence = soldierPersistence;
    }

    public Stream<Soldier> readAll() { return this.soldierPersistence.readAll(); }
}
