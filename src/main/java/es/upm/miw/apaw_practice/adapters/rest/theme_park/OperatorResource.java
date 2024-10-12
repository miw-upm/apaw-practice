package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.domain.services.theme_park.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OperatorResource.OPERATORS)
public class OperatorResource {
    public static final String OPERATORS = "/operators";

    static final String NAME_ID = "/{idEmployee}";

    private final OperatorService operatorService;

    @Autowired
    public OperatorResource(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String idEmployee) {
        this.operatorService.delete(idEmployee);
    }

}
