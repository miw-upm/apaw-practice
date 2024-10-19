package es.upm.miw.apaw_practice.domain.persistence_ports.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerECommercePersistence {
    CustomerECommerce findByUserName(String userName);
    void updateEmail(String userName, String newEmail);
}