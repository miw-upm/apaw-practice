package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class AttendeeEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String emailAddress;
    private String fullName;
    private String phoneNumber;

    // Reference to User by id
    private UUID user;

    public Attendee toAttendee() {
        return Attendee.builder()
                .emailAddress(this.emailAddress)
                .fullName(this.fullName)
                .phoneNumber(this.phoneNumber)
                .user(this.user != null ? UserDto.builder().id(this.user).build() : null)
                .build();
    }
}
