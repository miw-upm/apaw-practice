package es.upm.miw.apaw_practice.domain.persistence_ports.department;

import es.upm.miw.apaw_practice.domain.models.department.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerPersistence {

    Manager readByEmail(String email);

    void delete(String email);

    boolean existEmail(String email);


}
