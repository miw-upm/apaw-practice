package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.persistenceports.apiary.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    private final SalePersistence salePersistence;

    @Autowired
    public SaleService(SalePersistence salePersistence) {
        this.salePersistence = salePersistence;
    }


    public void delete(int idSale) {
        this.salePersistence.delete(idSale);
    }

}
