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

        if (!authorIds.isEmpty()) {
            java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById = this.userFinder.findByIds(authorIds).stream()
                    .collect(java.util.stream.Collectors.toMap(es.upm.miw.apaw.domain.model.UserSnapshot::getId, user -> user));

            summaries.forEach(summary -> summary.setAuthor(usersById.get(summary.getAuthorId())));
        }

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

        java.util.List<es.upm.miw.apaw.domain.model.UserSnapshot> users = this.userFinder.findByIds(authorIds);
        java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById = users.stream()
                .collect(java.util.stream.Collectors.toMap(es.upm.miw.apaw.domain.model.UserSnapshot::getId, user -> user));

        return works.stream()
                .map(work -> {
                    work.setAuthor(usersById.get(work.getAuthor().getId()));
                    return work;
                })
                .filter(work -> this.matchesAuthorFirstName(criteria, work))
                .toList();
    }

    private boolean matchesAuthorFirstName(es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria criteria, CreativeWork work) {
        // FALLO 2: Sin control de null en el criterio del usuario, rompe la ejecución si el cliente no lo envía
        return criteria.getAuthorFirstName().equals(work.getAuthor().getFirstName());
    }
}
