package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.ManagerRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.department.Manager;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.ManagerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("managerPersistence")
public class ManagerPersistenceMongodb implements ManagerPersistence {

    private ManagerRepository managerRepository;

    @Autowired
    public ManagerPersistenceMongodb(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager readByEmail(String email) {
        return this.managerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(" Manager email: " + email))
                .toManager();
    }

    @Override
    public boolean existEmail(String email) {
        return this.managerRepository
                .findByEmail(email)
                .isPresent();
    }

    @Override
    public void delete(String email) {
        this.managerRepository.deleteByEmail(email);
    }


}
