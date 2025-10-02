package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ApplicationEntity {
    @Id
    private UUID id;
    private Status status;
    private LocalDate date;
    private boolean isReferral;

    private UUID user;
    private UUID position;
    @DBRef
    private List<MeetingEntity> meetingList;
}
