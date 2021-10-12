package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.services.restaurant.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(ClientResource.CLIENTS)
public class ClientResource {
    static final String CLIENTS ="/restaurant/clients";
    static final String WAITERS ="/waiters";
    static final String ID_DNI = "/{dni}";

    private ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping(ID_DNI+WAITERS+WaiterResource.ID_SECTION+WaiterResource.CATEGORY)
    public Stream<Waiter> readCategoryByIdWaiterAndDniClient(@PathVariable String dni, @PathVariable String section){
        return this.clientService.readCategoryByIdWaiterAndDniClient(dni,section);
    }
}
