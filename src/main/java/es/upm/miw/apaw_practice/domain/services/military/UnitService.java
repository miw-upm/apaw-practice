package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.UnitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UnitService {
    private final UnitPersistence unitPersistence;

    @Autowired
    public UnitService(UnitPersistence unitPersistence) {
        this.unitPersistence = unitPersistence;
    }

    public Unit create(Unit unit) {
        this.assertNameNotExist(unit.getName());
        this.assertSoldiersNotInOtherUnit(unit.getSoldiers());
        return this.unitPersistence.create(unit);
    }

    public void assertNameNotExist(String name) {
        if (this.unitPersistence.existName(name)) {
            throw new ConflictException("Unit name already exists: " + name);
        }
    }

    public void assertSoldiersNotInOtherUnit(List<Soldier> soldiers) {
        if (this.findBySoldiers(soldiers).findAny().isPresent()) {
            throw new ConflictException("One or more soldiers are already assigned to another unit");
        }
    }

    public Stream<Unit> findBySoldiers(List<Soldier> soldiers) {
        List<String> identityDocuments = soldiers.stream()
                .map(Soldier::getIdentityDocument)
                .toList();
        return this.unitPersistence.readAll()
                .filter(unit -> unit.getSoldiers().stream()
                        .map(Soldier::getIdentityDocument)
                        .anyMatch(identityDocuments::contains));
    }
}
