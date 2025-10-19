package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.MembershipRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.MembershipEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.MembershipPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class MembershipPersistenceMongodb implements MembershipPersistence {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipPersistenceMongodb(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Stream<Membership> readAll() {
        return this.membershipRepository.findAll().stream()
                .map(MembershipEntity::toMembership);
    }

}
