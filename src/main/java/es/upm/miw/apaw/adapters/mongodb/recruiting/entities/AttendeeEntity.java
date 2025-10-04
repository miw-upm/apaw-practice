package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

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
    private String fullName;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String phoneNumber;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String emailAddress;

    // Reference to User by id
    private UUID user;
}
