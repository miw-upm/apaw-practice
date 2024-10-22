package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ExpenseBillRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.ExpenseBillPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("ExpenseBillPersistence")
public class ExpenseBillPersistenceMongodb implements ExpenseBillPersistence {

    private final ExpenseBillRepository expenseBillRepository;
    private CompanyPersistenceMongodb companyPersistence;

    @Autowired
    public ExpenseBillPersistenceMongodb(ExpenseBillRepository expenseBillRepository) {
        this.expenseBillRepository = expenseBillRepository;
    }

    @Override
    public void delete(String id) {
        if (!this.expenseBillRepository.existsById(id)) {
            throw new NotFoundException("Expense Bill id: " + id);
        }
        this.expenseBillRepository.deleteById(id);
    }



}
