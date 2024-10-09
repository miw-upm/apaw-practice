package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.services.bank.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClientResource.CLIENTS)
public class ClientResource {

    static final String CLIENTS = "/bank/clients";
    static final String SEARCH = "/search";
    static final String DNI = "/{dni}";

    private final ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(SEARCH)
    public Client findByDni(@RequestParam String dni) {
        return this.clientService.findByDni(dni);
    }

    @PatchMapping(DNI)
    public Client updateName(@PathVariable String dni, @RequestBody String name) {
        return this.clientService.updateName(dni,name);
    }
}
