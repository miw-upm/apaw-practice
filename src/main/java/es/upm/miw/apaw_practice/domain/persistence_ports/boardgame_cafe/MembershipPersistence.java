package es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MembershipPersistence {
    Stream<Membership> readAll();

    Membership create(Membership membership);

    Membership update(Integer membershipId, Membership membership);

    Membership read(Integer membershipId);

    boolean existMembershipId(Integer membershipId);
}
