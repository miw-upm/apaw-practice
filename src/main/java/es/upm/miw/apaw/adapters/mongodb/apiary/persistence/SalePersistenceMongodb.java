package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.SaleRepository;
import es.upm.miw.apaw.domain.persistenceports.apiary.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("salePersistence")
public class SalePersistenceMongodb implements SalePersistence {
    private final SaleRepository saleRepository;

    @Autowired
    public SalePersistenceMongodb(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void delete(int idSale) {
        this.saleRepository.deleteByIdSale(idSale);
    }
}

