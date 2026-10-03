package es.upm.miw.apaw.domain.ports.out.copyright;

import java.util.UUID;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria;

import java.util.List;

public interface CreativeWorkGateway {
    boolean existsById(UUID id);
    boolean existsByRegistrationCode(String registrationCode);
    CreativeWork create(CreativeWork creativeWork);
    List<CreativeWorkClaimSummary> generateClaimSummaries();
    List<CreativeWork> find(CreativeWorkFindCriteria criteria);
}
