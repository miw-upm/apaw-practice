package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.persistence_ports.company.ExpenseBillPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpenseBillService {
    private final ExpenseBillPersistence expenseBillPersistence;

    @Autowired
    public ExpenseBillService(ExpenseBillPersistence expenseBillPersistence) {
        this.expenseBillPersistence = expenseBillPersistence;
    }

    public void delete(String id) {
        this.expenseBillPersistence.delete(id);
    }

}
