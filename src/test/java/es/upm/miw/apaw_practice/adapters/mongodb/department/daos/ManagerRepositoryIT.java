package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class ManagerRepositoryIT {

    @Autowired
    private  ManagerRepository managerRepository;
}
