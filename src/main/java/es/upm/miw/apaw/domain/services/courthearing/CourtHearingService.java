package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.model.courthearing.CreationCourtHearing;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtGateway;
import es.upm.miw.apaw.domain.ports.out.courthearing.CourtHearingGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
}