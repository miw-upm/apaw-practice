package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpertServiceScheduleAdapter implements ExpertServiceScheduleGateway {

    private final ExpertServiceScheduleRepository expertServiceScheduleRepository;
    private final LegalExpertProfileRepository legalExpertProfileRepository;

    @Override
    @Transactional
    public ExpertServiceSchedule create(ExpertServiceSchedule expertServiceSchedule) {
        ExpertServiceScheduleEntity entity = new ExpertServiceScheduleEntity(expertServiceSchedule);

        List<LegalExpertProfileEntity> profileEntities = expertServiceSchedule.getLegalExpertProfiles().stream()
                .map(profile -> this.legalExpertProfileRepository.getReferenceById(profile.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        entity.setLegalExpertProfiles(profileEntities);
        this.expertServiceScheduleRepository.save(entity);
        return expertServiceSchedule;
    }

    @Override
    public boolean existsByTariffCode(String tariffCode) {
        return this.expertServiceScheduleRepository.existsByTariffCode(tariffCode);
    }
}