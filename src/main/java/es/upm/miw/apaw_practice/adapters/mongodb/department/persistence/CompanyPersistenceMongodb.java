package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.department.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyPersistenceMongodb implements CompanyPersistence {

    private CompanyPersistence companyPersistence;

    @Autowired
    public CompanyPersistenceMongodb(CompanyPersistence companyPersistence) {
        this.companyPersistence = companyPersistence;
    }
}
