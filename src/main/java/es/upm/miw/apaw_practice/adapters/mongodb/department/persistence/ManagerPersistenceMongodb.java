package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.department.ManagerPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerPersistenceMongodb implements ManagerPersistence {

    private ManagerPersistence managerPersistence;

    @Autowired
    public ManagerPersistenceMongodb(ManagerPersistence managerPersistence){
        this.managerPersistence = managerPersistence;
    }
}
