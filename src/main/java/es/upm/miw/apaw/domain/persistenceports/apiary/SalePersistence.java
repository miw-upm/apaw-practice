package es.upm.miw.apaw.domain.persistenceports.apiary;

import org.springframework.stereotype.Repository;

@Repository
public interface SalePersistence {

    void delete(int idSale);
}
