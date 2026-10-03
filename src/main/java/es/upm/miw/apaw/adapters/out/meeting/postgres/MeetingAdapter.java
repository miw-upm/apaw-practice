package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.ports.out.meeting.MeetingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingAdapter implements MeetingGateway {
    private final MeetingRepository meetingRepository;
}
