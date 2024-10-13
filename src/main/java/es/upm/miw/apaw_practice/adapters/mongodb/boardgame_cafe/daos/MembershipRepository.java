package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MembershipRepository extends MongoRepository<MembershipEntity, String> {
    Optional<MembershipEntity> findByType(String type);
}
