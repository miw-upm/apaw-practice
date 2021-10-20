package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.domain.models.department.Manager;
import es.upm.miw.apaw_practice.domain.services.department.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ManagerResource.MANAGERS)
public class ManagerResource {
    static final String MANAGERS = "/shop/managers";

    static final String EMAIL_ID = "/{email}";

    private final ManagerService managerService;

    @Autowired
    public ManagerResource(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping(EMAIL_ID)
    public Manager read(@PathVariable String email) {
        return this.managerService.read(email);
    }

    @DeleteMapping(EMAIL_ID)
    public void delete(@PathVariable String email) {
        this.managerService.delete(email);
    }
}
