package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingFindCriteria;
import es.upm.miw.apaw.domain.model.courthearing.CreationCourtHearing;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtHearingGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourtHearingService {
    private final CourtHearingGateway courtHearingGateway;
    private final CourtGateway courtGateway;
    private final UserFinder userFinder;

    public CourtHearing create(CreationCourtHearing creation) {
        this.assertCourtExists(creation.getCourtId());
        CourtHearing courtHearing = new CourtHearing();
        BeanUtils.copyProperties(creation, courtHearing);
        courtHearing.setAttendees(this.readAttendees(creation.getAttendeeIds()));
        courtHearing.doDefault();
        return this.courtHearingGateway.create(creation.getCourtId(), courtHearing);
    }

    private void assertCourtExists(UUID courtId) {
        this.courtGateway.read(courtId)
                .orElseThrow(() -> new NotFoundException("Court id not found: " + courtId));
    }

    private List<UserSnapshot> readAttendees(List<UUID> attendeeIds) {
        Set<UUID> ids = new LinkedHashSet<>(attendeeIds);
        List<UserSnapshot> users = this.userFinder.findByIds(ids);
        Set<UUID> foundIds = users.stream()
                .map(UserSnapshot::getId)
                .collect(Collectors.toSet());
        List<UUID> missingIds = ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
        if (!missingIds.isEmpty()) {
            throw new NotFoundException("User ids not found: " + missingIds);
        }
        return users;
    }

    public List<CourtHearing> find(CourtHearingFindCriteria criteria) {
        List<CourtHearing> courtHearings = this.courtHearingGateway.find(criteria);
        if (courtHearings.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = courtHearings.stream()
                .flatMap(courtHearing -> courtHearing.getAttendees().stream())
                .map(UserSnapshot::getId)
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, courtHearings, this.userFinder.findByIds(userIds));
    }

    private List<CourtHearing> toSummaries(
            CourtHearingFindCriteria criteria,
            List<CourtHearing> courtHearings,
            List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return courtHearings.stream()
                .map(courtHearing -> this.enrichAttendees(courtHearing, usersById))
                .filter(courtHearing -> this.matchesUserMobile(criteria, courtHearing))
                .map(CourtHearing::ofSummary)
                .toList();
    }

    private CourtHearing enrichAttendees(CourtHearing courtHearing, Map<UUID, UserSnapshot> usersById) {
        courtHearing.setAttendees(courtHearing.getAttendees().stream()
                .map(attendee -> this.findUser(attendee.getId(), usersById))
                .toList());
        return courtHearing;
    }

    private UserSnapshot findUser(UUID userId, Map<UUID, UserSnapshot> usersById) {
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        return user;
    }

    private boolean matchesUserMobile(CourtHearingFindCriteria criteria, CourtHearing courtHearing) {
        return !criteria.hasUserMobile() || courtHearing.getAttendees().stream()
                .anyMatch(attendee -> criteria.getUserMobile().equals(attendee.getMobile()));
    }
}