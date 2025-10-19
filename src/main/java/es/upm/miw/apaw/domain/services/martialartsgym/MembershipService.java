package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.MembershipPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MembershipService {

    private final MembershipPersistence membershipPersistence;

    @Autowired
    public MembershipService(MembershipPersistence membershipPersistence) {
        this.membershipPersistence = membershipPersistence;
    }

    public Stream<Membership> getAllMemberships() {
        return this.membershipPersistence.readAll();
    }
}
