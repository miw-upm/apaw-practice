package es.upm.miw.apaw.domain.ports.out.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;

public interface LegalExpertProfileGateway {
    LegalExpertProfile create(LegalExpertProfile legalExpertProfile);
}
