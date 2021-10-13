package es.upm.miw.apaw_practice.adapters.mongodb.restaurant;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.TableRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.WaiterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ReserveEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.TableEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.WaiterEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Service
public class RestaurantSeederService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private TableRepository tableRepository;

    public void seedDatabase(){
        LogManager.getLogger(this.getClass()).warn("----------- Restaurant Initial Load -----------");
        WaiterEntity[] waiters = {
                new WaiterEntity("terrace","employee"),
                new WaiterEntity("dining room","manager"),
                new WaiterEntity("terrace","manager"),
                new WaiterEntity("dining room","employee")
        };
        this.waiterRepository.saveAll(Arrays.asList(waiters));
        ReserveEntity[] reserves = {
                new ReserveEntity(LocalDate.of(2021,10,7),1,"Jose"),
                new ReserveEntity(LocalDate.of(2021,10,7),2,"Maria"),
                new ReserveEntity(LocalDate.of(2021,10,7),1,"Juan"),
                new ReserveEntity(LocalDate.of(2021,10,7),2,"Luis"),
                new ReserveEntity(LocalDate.of(2021,10,7),1,"Rosa"),
                new ReserveEntity(LocalDate.of(2021,10,8),2,"Antonio"),
                new ReserveEntity(LocalDate.of(2021,10,8),2,"Eva"),
                new ReserveEntity(LocalDate.of(2021,10,8),3,"Paco")
        };
        TableEntity[] tables = {
                new TableEntity(1,false,"classic",new BigDecimal(25.99), List.of(reserves[0],reserves[7])),
                new TableEntity(2,true,"urban",new BigDecimal(22.99), List.of(reserves[1])),
                new TableEntity(3,false,"classic",new BigDecimal(25.99), List.of(reserves[2])),
                new TableEntity(4,false,"urban",new BigDecimal(22.99), List.of(reserves[3],reserves[5])),
                new TableEntity(5,false,"classic",new BigDecimal(25.99), List.of(reserves[4],reserves[6]))
        };
        this.tableRepository.saveAll(Arrays.asList(tables));
        ClientEntity[] clients = {
                new ClientEntity("42279207D","Jose",LocalDate.of(2021,10,7),List.of(waiters),tables[0]),
                new ClientEntity("35935824A","Maria",LocalDate.of(2021,10,7),List.of(waiters),tables[1]),
                new ClientEntity("64221329Q","Belen",LocalDate.of(2021,10,7),List.of(waiters),tables[1]),
                new ClientEntity("91780147G","Juan",LocalDate.of(2021,10,7),List.of(waiters),tables[2]),
                new ClientEntity("82912110P","Luis",LocalDate.of(2021,10,7),List.of(waiters),tables[3]),
                new ClientEntity("89844561Y","Dona",LocalDate.of(2021,10,7),List.of(waiters),tables[3]),
                new ClientEntity("47604450Q","Rosa",LocalDate.of(2021,10,7),List.of(waiters),tables[4])
        };
        this.clientRepository.saveAll(Arrays.asList(clients));
    }

    public void deleteAll(){
        this.clientRepository.deleteAll();
        this.waiterRepository.deleteAll();
        this.tableRepository.deleteAll();
    }
}
