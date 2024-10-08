package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.CustomerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerNightLifeService {
    final private CustomerNightLifePersistence customerNightLifePersistence;
    @Autowired
    public CustomerNightLifeService(CustomerNightLifePersistence customerNightLifePersistence) {
        this.customerNightLifePersistence = customerNightLifePersistence;
    }
    public void delete(String name) {
        customerNightLifePersistence.delete(name);
    }

}
