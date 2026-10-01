package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LegalExpertProfileAdapter implements LegalExpertProfileGateway {

    private final LegalExpertProfileRepository legalExpertProfileRepository;

    @Override
    public LegalExpertProfile create(LegalExpertProfile legalExpertProfile) {
        LegalExpertProfileEntity entity = new LegalExpertProfileEntity(legalExpertProfile);
        return this.legalExpertProfileRepository.save(entity).toDomain();
    }

    @Override
    public boolean existsByTaxIdCode(String taxIdCode) {
        return this.legalExpertProfileRepository.existsByTaxIdCode(taxIdCode);
    }

    @Override
    public boolean existsByProfessionalLicense(String professionalLicense) {
        return this.legalExpertProfileRepository.existsByProfessionalLicense(professionalLicense);
    }

    @Override
    public LegalExpertProfile read(String id) {
        return this.legalExpertProfileRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Legal expert profile id: " + id))
                .toDomain();
    }
}