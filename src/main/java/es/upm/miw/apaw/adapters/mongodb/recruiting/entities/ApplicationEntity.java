package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
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
    private LocalDate created;
    private boolean referral;

    // Reference to User by id
    private UUID user;

    // Reference to Position by id
    private UUID position;

    //Embedded in the application (Composition)
    private List<MeetingEntity> meetingList;
}