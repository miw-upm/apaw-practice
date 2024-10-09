package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.EmployeeShoppingCenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.ProviderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.ShopRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos.TicketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.EmployeeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ProviderEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ShopEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.TicketEntity;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ShoppingCenterSeederService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private EmployeeShoppingCenterRepository employeeShoppingCenterRepositoryRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Shopping Center Initial Load -----------");
        ProviderEntity[] providers = {
                new ProviderEntity(new Provider("provider1", "food", true)),
                new ProviderEntity(new Provider("provider2", "tools", false)),
                new ProviderEntity(new Provider("provider3", "clothes", true))
        };
        this.providerRepository.saveAll(Arrays.asList(providers));
        EmployeeEntity[] employees = {
                new EmployeeEntity(new Employee("11122233A", "Alex", "600000001")),
                new EmployeeEntity(new Employee("11122233B", "Carlos", "600000002")),
                new EmployeeEntity(new Employee("11122233C", "Marta", "600000003")),
                new EmployeeEntity(new Employee("11122233D", "Juan", "600000004")),
                new EmployeeEntity(new Employee("11122233E", "Maria", "600000005"))
        };
        this.employeeShoppingCenterRepositoryRepository.saveAll(Arrays.asList(employees));
        ShopEntity[] shops = {
                new ShopEntity("shop1", "dir1", List.of(employees[0], employees[1]), List.of(providers[1])),
                new ShopEntity("shop2", "dir2", List.of(employees[2], employees[3]), List.of(providers[0], providers[1], providers[2])),
                new ShopEntity("shop3", "dir3", List.of(employees[4]), List.of(providers[0], providers[2]))
        };
        this.shopRepository.saveAll(Arrays.asList(shops));
        TicketEntity[] tickets = {
                new TicketEntity(new BigDecimal("4.23"), false, employees[0]),
                new TicketEntity(new BigDecimal("65.85"), true, employees[0]),
                new TicketEntity(new BigDecimal("1.54"), false, employees[1]),
                new TicketEntity(new BigDecimal("98.11"), true, employees[2]),
                new TicketEntity(new BigDecimal("26.98"), false, employees[2]),
                new TicketEntity(new BigDecimal("23.67"), false, employees[3]),
                new TicketEntity(new BigDecimal("127.43"), true, employees[4]),
        };
        this.ticketRepository.saveAll(Arrays.asList(tickets));
    }

    public void deleteAll() {
        this.shopRepository.deleteAll();
        this.providerRepository.deleteAll();
        this.employeeShoppingCenterRepositoryRepository.deleteAll();
        this.ticketRepository.deleteAll();
    }
}
