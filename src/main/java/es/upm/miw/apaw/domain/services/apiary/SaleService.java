package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import es.upm.miw.apaw.domain.persistenceports.apiary.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    public Sale create(Sale sale) {
        this.assertIdSaleNotExist(sale.getIdSale());
        return this.salePersistence.create(sale);
    }

    public void assertIdSaleNotExist(int idSale) {
        if (this.salePersistence.existIdSale(idSale)) {
            throw new ConflictException("idSale exist: " + idSale);
        }
    }
}
