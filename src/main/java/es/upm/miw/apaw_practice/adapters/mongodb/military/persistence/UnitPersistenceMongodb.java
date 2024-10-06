package es.upm.miw.apaw_practice.adapters.mongodb.military.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.SoldierRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.UnitRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.UnitEntity;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.UnitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("unitPersistence")
public class UnitPersistenceMongodb implements UnitPersistence {
    private final UnitRepository unitRepository;
    private final SoldierRepository soldierRepository;

    @Autowired
    public UnitPersistenceMongodb(UnitRepository unitRepository, SoldierRepository soldierRepository) {
        this.unitRepository = unitRepository;
        this.soldierRepository = soldierRepository;
    }

    @Override
    public Unit create(Unit unit) {
        List<String> identityDocuments = unit.getSoldiers().stream()
                .map(Soldier::getIdentityDocument)
                .toList();

        List<SoldierEntity> soldierEntities = this.soldierRepository.findByIdentityDocumentIn(identityDocuments);
        return this.unitRepository
                .save(new UnitEntity(unit.getName(), unit.getBranch(), unit.getLocation(), soldierEntities))
                .toUnit();
    }

    @Override
    public boolean existName(String name) {
        return this.unitRepository
                .findByName(name)
                .isPresent();
    }

    @Override
    public Stream<Unit> readAll() {
        return this.unitRepository.findAll().stream()
                .map(UnitEntity::toUnit);
    }
}
