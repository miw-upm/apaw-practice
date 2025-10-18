package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.MembershipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MembershipRepository extends MongoRepository<MembershipEntity, UUID> {
}
