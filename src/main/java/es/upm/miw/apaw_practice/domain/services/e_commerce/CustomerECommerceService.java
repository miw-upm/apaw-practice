package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce.CustomerECommercePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CustomerECommerceService {

    private final CustomerECommercePersistence customerPersistence;

    @Autowired
    public CustomerECommerceService(CustomerECommercePersistence customerECommercePersistence) {
        this.customerPersistence = customerECommercePersistence;
    }

    public CustomerECommerce findByUserName(String userName) {
        return this.customerPersistence.findByUserName(userName);
    }
}