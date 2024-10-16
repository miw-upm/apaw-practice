package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Management;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.ManagementPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {
    private final ManagementPersistence managementPersistence;

    @Autowired
    public ManagementService(ManagementPersistence managementPersistence) {
        this.managementPersistence = managementPersistence;
    }

    public Management create(Management management) {
        return this.managementPersistence.create(management);
    }
}