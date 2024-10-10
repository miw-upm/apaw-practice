package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import es.upm.miw.apaw_practice.domain.services.night_life.CustomerNightLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerNightLifeResource.CUSTOMERS)
public class CustomerNightLifeResource {
    static final String CUSTOMERS = "/night-life/customers";
    static final String NAME_ID = "/{name}";
    private final CustomerNightLifeService customerNightLifeService;

    @Autowired
    public CustomerNightLifeResource(CustomerNightLifeService customerNightLifeService) {
        this.customerNightLifeService = customerNightLifeService;
    }
    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name){
        this.customerNightLifeService.delete(name);
    }

    @PutMapping(NAME_ID)
    public Customer update(@PathVariable String name, @RequestBody Customer customer){
        return this.customerNightLifeService.update(name, customer);
    }
}
