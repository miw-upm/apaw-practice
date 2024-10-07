package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.services.boardgame_cafe.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MembershipResource.MEMBERSHIP)
public class MembershipResource {

    static final String MEMBERSHIP = "/boardgame-cafe/membership";

    private final MembershipService membershipService;

    @Autowired
    public MembershipResource(MembershipService membershipService) {
        this.membershipService = membershipService;
    }
}
