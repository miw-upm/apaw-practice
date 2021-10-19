package es.upm.miw.apaw_practice.domain.services.department;

import es.upm.miw.apaw_practice.domain.models.department.Manager;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.ManagerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerPersistence managerPersistence;

    @Autowired
    public ManagerService(ManagerPersistence managerPersistence) {
        this.managerPersistence = managerPersistence;
    }

    public Manager read(String email) {
        return this.managerPersistence.readByEmail(email);
    }

    public void delete(String email) {
        this.managerPersistence.delete(email);
    }
}
