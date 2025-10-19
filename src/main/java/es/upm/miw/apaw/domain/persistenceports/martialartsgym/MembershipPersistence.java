package es.upm.miw.apaw.domain.persistenceports.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;

import java.util.stream.Stream;

public interface MembershipPersistence {

    Stream<Membership> readAll();

}
