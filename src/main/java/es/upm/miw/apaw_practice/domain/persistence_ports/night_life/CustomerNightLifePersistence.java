package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerNightLifePersistence {
    void delete(String name);
    Customer update(String name, Customer customer);
}
