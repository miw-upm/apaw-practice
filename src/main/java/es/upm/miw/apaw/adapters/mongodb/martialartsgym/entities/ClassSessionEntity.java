package es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.martialartsgym.ClassSession;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ClassSessionEntity {

    @Id
    private Integer referenceCode;

    private Integer sessionLength;
    private String difficultyLevel;

    @DBRef
    private DojoEntity dojo;

    private List<UUID> attendeeIds;

    public ClassSession toClassSession() {
        return ClassSession.builder()
                .referenceCode(this.referenceCode)
                .sessionLength(this.sessionLength)
                .difficultyLevel(this.difficultyLevel)
                .dojo(this.dojo == null ? null : this.dojo.toDojo())
                .attendees(this.attendeeIds == null ? List.of() :
                        this.attendeeIds.stream()
                                .map(id -> UserDto.builder().id(id).build())
                                .toList())
                .build();
    }
}
