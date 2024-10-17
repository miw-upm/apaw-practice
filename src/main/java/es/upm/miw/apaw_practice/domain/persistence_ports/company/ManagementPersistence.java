package es.upm.miw.apaw_practice.domain.persistence_ports.company;

import es.upm.miw.apaw_practice.domain.models.company.Management;

import org.springframework.stereotype.Repository;

@Repository
public interface ManagementPersistence {

    Management create(Management management);

}
