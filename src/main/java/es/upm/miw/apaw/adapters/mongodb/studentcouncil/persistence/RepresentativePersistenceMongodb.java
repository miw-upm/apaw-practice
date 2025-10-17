package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.RepresentativeRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.RepresentativeEntity;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.RepresentativePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("representativePersistence")
public class RepresentativePersistenceMongodb implements RepresentativePersistence {

    private final RepresentativeRepository representativeRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public RepresentativePersistenceMongodb(RepresentativeRepository representativeRepository, UserRestClient userRestClient) {
        this.representativeRepository = representativeRepository;
        this.userRestClient = userRestClient;
    }

    @Override
    public Stream<Representative> readAll() {
        return this.representativeRepository.findAll()
                .stream()
                .map(RepresentativeEntity::toRepresentative);
    }

    @Override
    public List<String> findUserMobilesByReplyReason(String reason) {
        return this.representativeRepository.findAll().stream()
                .filter(rep -> rep.getTopics() != null)
                .filter(rep -> rep.getTopics().stream()
                        .anyMatch(issue -> issue.getReplies() != null &&
                                issue.getReplies().stream()
                                        .anyMatch(reply -> reason.equalsIgnoreCase(reply.getReason()))
                        )
                )
                .map(rep -> {
                    UUID userId = rep.getRepresentativeId();
                    if (userId == null) {
                        return null;
                    }
                    return userRestClient.readById(userId).getMobile();
                })
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
}
