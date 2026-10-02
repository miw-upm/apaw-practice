package es.upm.miw.apaw.domain.ports.out.expertdirectoryservices;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import java.util.stream.Stream;

public interface LegalExpertProfileGateway {

    LegalExpertProfile create(LegalExpertProfile legalExpertProfile);

    boolean existsByTaxIdCode(String taxIdCode);

    boolean existsByProfessionalLicense(String professionalLicense);

    LegalExpertProfile read(String id);

    LegalExpertProfile update(LegalExpertProfile legalExpertProfile);

    void delete(String id);

    Stream<LegalExpertProfile> findAll();
}