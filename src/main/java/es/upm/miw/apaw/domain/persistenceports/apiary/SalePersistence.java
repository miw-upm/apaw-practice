package es.upm.miw.apaw.domain.persistenceports.apiary;

import es.upm.miw.apaw.domain.models.apiary.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePersistence {

    void delete(int idSale);

    Sale create(Sale sale);

    boolean existIdSale(int idSale);
}
