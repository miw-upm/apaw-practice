package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import es.upm.miw.apaw_practice.domain.services.football.PrincipalRefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PrincipalRefereeResource.PRINCIPALREFEREES)
public class PrincipalRefereeResource {

    static final String PRINCIPALREFEREES = "/football/principalReferees";

    private final PrincipalRefereeService principalRefereeService;

    @Autowired
    public PrincipalRefereeResource(PrincipalRefereeService principalRefereeService) {
        this.principalRefereeService = principalRefereeService;
    }

    @PostMapping
    public PrincipalReferee create(@RequestBody PrincipalReferee principalReferee) {
        return this.principalRefereeService.create(principalReferee);
    }

}
