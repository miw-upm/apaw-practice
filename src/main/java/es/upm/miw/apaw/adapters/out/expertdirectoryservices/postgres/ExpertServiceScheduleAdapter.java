package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExpertServiceScheduleAdapter implements ExpertServiceScheduleGateway {

    private final ExpertServiceScheduleRepository expertServiceScheduleRepository;
    private final LegalExpertProfileRepository legalExpertProfileRepository;

    @Override
    @Transactional
    public ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule) {
        List<UUID> profileIds = expertServiceSchedule.getLegalExpertProfiles().stream()
                .map(LegalExpertProfile::getId)
                .toList();
        List<LegalExpertProfileEntity> profileEntities = this.legalExpertProfileRepository.findAllById(profileIds)
                .stream()
                .sorted(Comparator.comparingInt(profile -> profileIds.indexOf(profile.getId())))
                .toList();

        ExpertServiceScheduleEntity entity = new ExpertServiceScheduleEntity(expertServiceSchedule);
        entity.setLegalExpertProfiles(new ArrayList<>(profileEntities));
        return this.expertServiceScheduleRepository.save(entity).toDomain();
    }

    @Override
    public boolean existsByTariffCode(String tariffCode) {
        return this.expertServiceScheduleRepository.existsByTariffCode(tariffCode);
    }

    @Override
    public boolean existsByLegalExpertProfileIds(Collection<UUID> legalExpertProfileIds) {
        return this.expertServiceScheduleRepository.existsByLegalExpertProfilesIdIn(legalExpertProfileIds);
    }
}