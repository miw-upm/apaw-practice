package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.domain.services.gun_store.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SetupResource.SETUPS)
public class SetupResource {
    static final String SETUPS = "/gunstore/setups";

    static final String ID = "/{id}";

    private final SetupService setupService;

    @Autowired
    public SetupResource(SetupService setupService) {
        this.setupService = setupService;
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Integer id){
        this.setupService.delete(id);
    }

}
