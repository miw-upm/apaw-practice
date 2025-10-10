package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class MeetingEntity {
    @Id
    private UUID id;
    private LocalDateTime date;
    private String url;

    @DBRef
    private List<AttendeeEntity> attendees;

    public Meeting toMeeting() {
        List<Attendee> attendeeList = (this.attendees == null)
                ? Collections.emptyList()
                : this.attendees.stream()
                .map(AttendeeEntity::toAttendee)
                .toList();

        return new Meeting(this.date, this.url, attendeeList);
    }
}
