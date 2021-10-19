package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
import es.upm.miw.apaw_practice.domain.services.restaurant.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClientResource.CLIENTS)
public class ClientResource {
    static final String CLIENTS ="/restaurant/clients";
    static final String NAME ="/name";
    static final String ID_DNI = "/{dni}";

    private ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping(ID_DNI+NAME)
    public Client findNameByDni(@PathVariable String dni){
        return Client.ofName(this.clientService.read(dni));
    }

    @DeleteMapping(ID_DNI)
    public void delete(String dni){
        this.clientService.delete(dni);
    }
}
