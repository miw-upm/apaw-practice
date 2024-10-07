package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.MembershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.MembershipPersistence;
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
        return membershipRepository
                .findAll()
                .stream()
                .map(MembershipEntity::toMembership);
    }

    @Override
    public Membership create(Membership membership) {
        return membershipRepository
                .save(new MembershipEntity(membership))
                .toMembership();
    }

    @Override
    public Membership update(Integer membershipId, Membership membership) {
        MembershipEntity membershipEntity = membershipRepository
                .findByMembershipId(membershipId)
                .orElseThrow(() -> new NotFoundException("Membership Id: " + membershipId));
        membershipEntity.fromMembership(membership);
        return membershipRepository
                .save(membershipEntity)
                .toMembership();
    }

    @Override
    public Membership read(Integer membershipId) {
        return membershipRepository
                .findByMembershipId(membershipId)
                .orElseThrow(() -> new NotFoundException("Membership Id: " + membershipId))
                .toMembership();
    }

    @Override
    public boolean existMembershipId(Integer membershipId) {
        return membershipRepository
                .findByMembershipId(membershipId)
                .isPresent();
    }
}
