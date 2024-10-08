package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.CustomerNightLifeRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.CustomerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerNightLifePersistence")
public class CustomerNightLifePersistenceMongodb implements CustomerNightLifePersistence {
    private final CustomerNightLifeRepository customerNightLifeRepository;
    @Autowired
    public CustomerNightLifePersistenceMongodb(CustomerNightLifeRepository customerNightLifeRepository) {
        this.customerNightLifeRepository = customerNightLifeRepository;
    }
    @Override
    public void delete(String name) {
        this.customerNightLifeRepository.deleteByName(name);
    }
}
