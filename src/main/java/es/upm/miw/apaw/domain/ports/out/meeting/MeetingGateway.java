package es.upm.miw.apaw.domain.ports.out.meeting;

import es.upm.miw.apaw.domain.model.meeting.Meeting;

public interface MeetingGateway {
    Meeting create(Meeting meeting);

    boolean existsByTitle(String title);
}
