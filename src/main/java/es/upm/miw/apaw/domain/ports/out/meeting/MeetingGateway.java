package es.upm.miw.apaw.domain.ports.out.meeting;

import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingFindCriteria;

import java.util.List;

public interface MeetingGateway {
    Meeting create(Meeting meeting);

    List<Meeting> find(MeetingFindCriteria criteria);

    boolean existsByTitle(String title);
}
