package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import es.upm.miw.apaw.domain.persistenceports.apiary.SalePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;


@Service
public class SaleService {

    private final SalePersistence salePersistence;
    private final UserRestClient userRestClient;


    @Autowired
    public SaleService(SalePersistence salePersistence,UserRestClient userRestClient) {
        this.salePersistence = salePersistence;
        this.userRestClient = userRestClient;
    }


    public void delete(int idSale) {
        this.salePersistence.delete(idSale);
    }


    public Sale create(@Valid Sale sale) {
        sale.setIdSale(new Random().nextInt());
        UserDto clientDto = this.userRestClient.readById(sale.getClient().getId());
        sale.setClient(clientDto);
        Sale saleDb = this.salePersistence.create(sale);
        saleDb.setClient(clientDto);
        return saleDb;
    }

    public void assertIdSaleNotExist(int idSale) {
        if (this.salePersistence.existIdSale(idSale)) {
            throw new ConflictException("idSale exist: " + idSale);
        }
    }
}
