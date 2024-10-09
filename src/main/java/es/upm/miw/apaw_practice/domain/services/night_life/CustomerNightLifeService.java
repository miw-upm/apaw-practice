package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.CustomerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerNightLifeService {
    private final CustomerNightLifePersistence customerNightLifePersistence;
    @Autowired
    public CustomerNightLifeService(CustomerNightLifePersistence customerNightLifePersistence) {
        this.customerNightLifePersistence = customerNightLifePersistence;
    }
    public void delete(String name) {
        customerNightLifePersistence.delete(name);
    }

    public Customer update(String name, Customer customer) {
        return this.customerNightLifePersistence.update(name,customer);
    }
}
