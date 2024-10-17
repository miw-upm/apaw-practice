package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ManagementRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ManagementEntity;
import es.upm.miw.apaw_practice.domain.models.company.Management;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.ManagementPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("managementPersistence")
public class ManagementPersistenceMongodb implements ManagementPersistence {
    private final ManagementRepository managementRepository;

    @Autowired
    public ManagementPersistenceMongodb(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    public Management create(Management management) {
        ManagementEntity managementEntity = new ManagementEntity();
        managementEntity.setName(management.getName());
        managementEntity.setActivated(management.isActivated());
        return this.managementRepository.save(managementEntity).toManagement();
    }

}