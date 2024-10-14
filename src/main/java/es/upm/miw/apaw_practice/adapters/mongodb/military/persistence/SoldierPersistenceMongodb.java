package es.upm.miw.apaw_practice.adapters.mongodb.military.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.SoldierRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.SoldierPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("soldierPersistence")
public class SoldierPersistenceMongodb implements SoldierPersistence {
    private final SoldierRepository soldierRepository;

    @Autowired
    public SoldierPersistenceMongodb(SoldierRepository soldierRepository) {
        this.soldierRepository = soldierRepository;
    }

    @Override
    public Stream<Soldier> readAll() {
        return this.soldierRepository.findAll().stream()
                .map(SoldierEntity::toSoldier);
    }

    @Override
    public Soldier read(String identityDocument) {
        return this.soldierRepository
                .findByIdentityDocument(identityDocument)
                .orElseThrow(() -> new NotFoundException("Soldier identity document: " + identityDocument))
                .toSoldier();
    }

    @Override
    public Soldier update(String identityDocument, Soldier soldier) {
        SoldierEntity soldierEntity = this.soldierRepository
                .findByIdentityDocument(identityDocument)
                .orElseThrow(() -> new NotFoundException("Soldier identity document: " + identityDocument));
        soldierEntity.fromSoldier(soldier.getIdentityDocument(), soldier.getFullName(), soldier.getRank(), soldier.getBirthDate());
        return this.soldierRepository
                .save(soldierEntity)
                .toSoldier();
    }
}
