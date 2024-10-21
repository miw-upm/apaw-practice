package es.upm.miw.apaw_practice.domain.persistence_ports.company;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExpenseBillPersistence {

    void delete(String id);

}
