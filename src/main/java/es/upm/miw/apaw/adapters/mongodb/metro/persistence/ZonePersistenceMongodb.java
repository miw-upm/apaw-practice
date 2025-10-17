package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.ZoneRepository;
import es.upm.miw.apaw.adapters.mongodb.metro.entities.ZoneEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.metro.Zone;
import es.upm.miw.apaw.domain.persistenceports.metro.ZonePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("zonePersistence")
public class ZonePersistenceMongodb implements ZonePersistence {
    private final ZoneRepository zoneRepository;

    @Autowired
    public ZonePersistenceMongodb(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone getByType(String type) {
        return this.zoneRepository
                .findByType(type)
                .orElseThrow(() -> new NotFoundException("Zone not found with type: " + type))
                .toZone();
    }

    @Override
    public Zone update(String type, Zone zone) {
        return this.zoneRepository
                .save(new ZoneEntity(zone))
                .toZone();
    }

    @Override
    public boolean existType(String type) {
        return this.zoneRepository
                .findByType(type)
                .isPresent();
    }

}
