package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria.ExpertServiceScheduleFindCriteria;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.ExpertServiceScheduleGateway;
import es.upm.miw.apaw.domain.ports.out.expertdirectoryservices.LegalExpertProfileGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceScheduleService {

    private final ExpertServiceScheduleGateway expertServiceScheduleGateway;
    private final LegalExpertProfileGateway legalExpertProfileGateway;
    private final UserFinder userFinder;

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

    public List<ExpertServiceSchedule> find(ExpertServiceScheduleFindCriteria criteria) {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleGateway.find(criteria);
        Map<UUID, UserSnapshot> usersById = this.findUsersById(schedules);
        schedules.forEach(schedule -> schedule.getLegalExpertProfiles()
                .forEach(profile -> profile.setUserSnapshot(this.readUser(usersById, profile))));
        return schedules.stream()
                .filter(schedule -> this.matchesUserEmail(criteria, schedule))
                .map(this::toSummary)
                .toList();
    }

    private Map<UUID, UserSnapshot> findUsersById(List<ExpertServiceSchedule> schedules) {
        Set<UUID> userIds = schedules.stream()
                .flatMap(schedule -> schedule.getLegalExpertProfiles().stream())
                .map(profile -> profile.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        if (userIds.isEmpty()) {
            return Map.of();
        }
        return this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
    }

    private UserSnapshot readUser(Map<UUID, UserSnapshot> usersById, LegalExpertProfile profile) {
        UUID userId = profile.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        return user;
    }

    private boolean matchesUserEmail(ExpertServiceScheduleFindCriteria criteria, ExpertServiceSchedule schedule) {
        return !criteria.hasUserEmail() || schedule.getLegalExpertProfiles().stream()
                .anyMatch(profile -> criteria.getUserEmail().trim()
                        .equalsIgnoreCase(profile.getUserSnapshot().getEmail()));
    }

    private ExpertServiceSchedule toSummary(ExpertServiceSchedule schedule) {
        schedule.setLegalExpertProfiles(schedule.getLegalExpertProfiles().stream()
                .map(LegalExpertProfile::ofSummary)
                .toList());
        return schedule;
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
