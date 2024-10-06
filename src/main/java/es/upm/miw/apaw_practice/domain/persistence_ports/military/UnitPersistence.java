package es.upm.miw.apaw_practice.domain.persistence_ports.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface UnitPersistence {
    Unit create(Unit unit);
    boolean existName(String name);
    Stream<Unit> readAll();
}
