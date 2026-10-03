package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation;
import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreativeWorkService {
    private final CreativeWorkGateway creativeWorkGateway;
    private final UserFinder userFinder;

    public CreativeWork create(CreativeWorkCreation creation) {
        if (this.creativeWorkGateway.existsByRegistrationCode(creation.getRegistrationCode())) {
            throw new ConflictException("Registration code already exists: " + creation.getRegistrationCode());
        }

        CreativeWork creativeWork = new CreativeWork();
        BeanUtils.copyProperties(creation, creativeWork, "authorId");
        creativeWork.setAuthor(this.userFinder.read(creation.getAuthorId()));
        creativeWork.doDefault();

        return this.creativeWorkGateway.create(creativeWork);
    }

    public java.util.List<es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary> generateClaimSummaries() {
        java.util.List<es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary> summaries = 
                this.creativeWorkGateway.generateClaimSummaries();

        java.util.Set<UUID> authorIds = summaries.stream()
                .map(es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary::getAuthorId)
                .collect(java.util.stream.Collectors.toSet());

        java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById = this.fetchUsersMap(authorIds);
        summaries.forEach(summary -> this.enrichAuthor(summary, usersById));

        return summaries;
    }

    public java.util.List<CreativeWork> find(es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria criteria) {
        java.util.List<CreativeWork> works = this.creativeWorkGateway.find(criteria);
        if (works.isEmpty()) {
            return java.util.List.of();
        }

        java.util.Set<UUID> authorIds = works.stream()
                .map(work -> work.getAuthor().getId())
                .collect(java.util.stream.Collectors.toSet());

        java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById = this.fetchUsersMap(authorIds);

        return works.stream()
                .map(work -> this.enrichAuthor(work, usersById))
                .filter(work -> this.matchesAuthorFirstName(criteria, work))
                .toList();
    }

    private java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> fetchUsersMap(java.util.Set<UUID> userIds) {
        if (userIds.isEmpty()) {
            return java.util.Map.of();
        }
        return this.userFinder.findByIds(userIds).stream()
                .collect(java.util.stream.Collectors.toMap(es.upm.miw.apaw.domain.model.UserSnapshot::getId, user -> user));
    }

    private void enrichAuthor(es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary summary, java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById) {
        es.upm.miw.apaw.domain.model.UserSnapshot user = usersById.get(summary.getAuthorId());
        if (user != null) {
            summary.setAuthor(user);
        }
    }

    private CreativeWork enrichAuthor(CreativeWork work, java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById) {
        es.upm.miw.apaw.domain.model.UserSnapshot user = usersById.get(work.getAuthor().getId());
        if (user != null) {
            work.setAuthor(user);
        }
        return work;
    }

    private boolean matchesAuthorFirstName(es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria criteria, CreativeWork work) {
        if (!criteria.hasAuthorFirstName()) {
            return true;
        }
        return work.getAuthor() != null && criteria.getAuthorFirstName().equals(work.getAuthor().getFirstName());
    }
}
