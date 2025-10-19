package es.upm.miw.apaw.adapters.resources.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import es.upm.miw.apaw.domain.services.martialartsgym.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(MembershipResource.MEMBERSHIPS)
public class MembershipResource {

    public static final String MEMBERSHIPS = "/memberships";

    private final MembershipService membershipService;

    @Autowired
    public MembershipResource(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping
    public Stream<Membership> getAllMemberships() {
        return this.membershipService.getAllMemberships();
    }
}
