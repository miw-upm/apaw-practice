package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Application;
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
    private LocalDate created;
    private boolean referral;

    // Reference to User by id
    private UUID user;

    // Reference to Position
    @DBRef
    private PositionEntity positionEntity;

    // Embedded in the application (Composition)
    private List<MeetingEntity> meetingList;

    public Application toApplication() {
        return Application.builder()
                .id(this.id)
                .status(this.status)
                .created(this.created)
                .referral(this.referral)
                .user(UserDto.builder().id(user).build())
                .position(positionEntity != null ? positionEntity.toPosition() : null)
                .meetingList(this.meetingList == null ? null :
                        this.meetingList.stream()
                                .map(MeetingEntity::toMeeting)
                                .toList())
                .build();
    }

    public boolean getReferral() {
      return this.referral;
    }
}