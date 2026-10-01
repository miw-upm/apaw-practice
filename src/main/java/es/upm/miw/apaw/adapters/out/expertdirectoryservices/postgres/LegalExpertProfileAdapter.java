package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalExpertProfileAdapter implements LegalExpertProfileGateway {
    private final LegalExpertProfileRepository legalExpertProfileRepository;

    @Override
    public LegalExpertProfile create(LegalExpertProfile legalExpertProfile) {
        LegalExpertProfileEntity entity = new LegalExpertProfileEntity(legalExpertProfile);

        return this.legalExpertProfileRepository.save(entity).toDomain();
    }
}