package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceScheduleService {

    private final ExpertServiceScheduleGateway expertServiceScheduleGateway;
    private final LegalExpertProfileGateway legalExpertProfileGateway;

    public ExpertServiceSchedule create(CreationExpertServiceSchedule creation) {
        if (this.expertServiceScheduleGateway.existsByTariffCode(creation.getTariffCode())) {
            throw new ConflictException("Ya existe una tarifa con este código: " + creation.getTariffCode());
        }
        List<UUID> profileIds = this.distinctProfileIds(creation);
        if (this.expertServiceScheduleGateway.existsByLegalExpertProfileIds(profileIds)) {
            throw new ConflictException("Algún perfil ya está asociado a otra tarifa: " + profileIds);
        }

        ExpertServiceSchedule schedule = new ExpertServiceSchedule();
        BeanUtils.copyProperties(creation, schedule);
        schedule.setLegalExpertProfiles(this.readLegalExpertProfiles(profileIds));
        schedule.doDefault();
        return this.expertServiceScheduleGateway.create(schedule);
    }

    private List<UUID> distinctProfileIds(CreationExpertServiceSchedule creation) {
        List<UUID> profileIds = creation.getLegalExpertProfileIds() == null
                ? List.of()
                : creation.getLegalExpertProfileIds();
        if (Set.copyOf(profileIds).size() != profileIds.size()) {
            throw new BadRequestException("Perfiles duplicados en la petición: " + profileIds);
        }
        return profileIds;
    }

    private List<LegalExpertProfile> readLegalExpertProfiles(List<UUID> profileIds) {
        List<LegalExpertProfile> profiles = this.legalExpertProfileGateway.readAllByIds(profileIds);
        Set<UUID> foundIds = profiles.stream().map(LegalExpertProfile::getId).collect(Collectors.toSet());
        List<UUID> missingIds = profileIds.stream().filter(id -> !foundIds.contains(id)).toList();
        if (!missingIds.isEmpty()) {
            throw new NotFoundException("Legal expert profile ids: " + missingIds);
        }
        return profiles;
    }
}
