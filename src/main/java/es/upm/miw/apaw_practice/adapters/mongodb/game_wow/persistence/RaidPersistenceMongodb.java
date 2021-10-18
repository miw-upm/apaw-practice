package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.RaidRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.RaidEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("raidPersistence")
public class RaidPersistenceMongodb implements RaidPersistence {

    private RaidRepository raidRepository;

    @Autowired
    public RaidPersistenceMongodb(RaidRepository raidRepository) {
        this.raidRepository = raidRepository;
    }

    @Override
    public Raid readById(String id) {
        return this.raidRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("raid id:" + id))
                .toRaid();
    }

    @Override
    public Raid create(Raid raid) {
        RaidEntity raidEntity = new RaidEntity(raid);
        return raidRepository
                .save(raidEntity)
                .toRaid();
    }

    @Override
    public Raid update(Raid raid) {
        RaidEntity raidEntity = this.raidRepository
                .findById(raid.getId())
                .orElseThrow(() -> new NotFoundException("Raid id: " + raid.getId()));
        raidEntity.fromRaid(raid);
        return this.raidRepository
                .save(raidEntity)
                .toRaid();
    }

    @Override
    public Stream<Raid> readAll() {
        return this.raidRepository.findAll().stream()
                .map(RaidEntity::toRaid);
    }
}
