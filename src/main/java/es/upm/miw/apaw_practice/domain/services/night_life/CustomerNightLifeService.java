package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.CustomerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerNightLifeService {
    private final CustomerNightLifePersistence customerNightLifePersistence;
    private final ClubPersistence clubPersistence;
    @Autowired
    public CustomerNightLifeService(CustomerNightLifePersistence customerNightLifePersistence, ClubPersistence clubPersistence) {
        this.customerNightLifePersistence = customerNightLifePersistence;
        this.clubPersistence = clubPersistence;
    }
    public void delete(String name) {
        customerNightLifePersistence.delete(name);
    }

    public Customer update(String name, Customer customer) {
        return this.customerNightLifePersistence.update(name,customer);
    }
    public List<String> getOwnerPhonesByCustomerEmail(String email) {
        return this.clubPersistence.readAll()
                .filter(club -> club.getReservations().stream()
                        .flatMap(reservation -> reservation.getCustomers().stream())
                        .anyMatch(customer -> customer.getEmail().equals(email)))
                .map(club -> club.getOwner().getPhone())
                .distinct()
                .toList();
    }
}
