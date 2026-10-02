package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpertServiceScheduleService {

    private final ExpertServiceScheduleGateway expertServiceScheduleGateway;
    private final LegalExpertProfileGateway legalExpertProfileGateway;

    public ExpertServiceSchedule create(CreationExpertServiceSchedule creation) {
        if (this.expertServiceScheduleGateway.existsByTariffCode(creation.getTariffCode())) {
            throw new ConflictException("Ya existe una tarifa con este código: " + creation.getTariffCode());
        }

        ExpertServiceSchedule schedule = new ExpertServiceSchedule();
        BeanUtils.copyProperties(creation, schedule);

        if (creation.getLegalExpertProfileIds() != null) {
            schedule.setLegalExpertProfiles(creation.getLegalExpertProfileIds().stream()
                    .map(this::readLegalExpertProfile)
                    .toList());
        } else {
            schedule.setLegalExpertProfiles(new ArrayList<>());
        }

        schedule.doDefault();
        return this.expertServiceScheduleGateway.create(schedule);
    }

    private LegalExpertProfile readLegalExpertProfile(UUID id) {
        return this.legalExpertProfileGateway.read(id.toString());
    }
}