package es.upm.miw.apaw_practice.domain.persistence_ports.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface SoldierPersistence {
    Stream<Soldier> readAll();
    Soldier read(String identityDocument);
    Soldier update(String identityDocument, Soldier soldier);
}
