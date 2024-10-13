package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.MembershipPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    private final MembershipPersistence membershipPersistence;

    @Autowired
    public MembershipService(MembershipPersistence membershipPersistence) {
        this.membershipPersistence = membershipPersistence;
    }

    public Membership readByType(String type) {
        return this.membershipPersistence.read(type);
    }
}
