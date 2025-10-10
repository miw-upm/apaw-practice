package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class RepresentativeEntity {
    @Id
    private UUID id;

    private LocalDateTime joinDate;
    private String responsibility;
    private UUID representativeId;
    private List<StudentIssueEntity> topics;

    public Representative toRepresentative() {
        return Representative.builder()
                .joinDate(this.joinDate)
                .responsibility(this.responsibility)
                .representative(
                        UserDto.builder()
                                .id(this.representativeId)
                                .build()
                )
                .topics(
                        this.topics == null ? new ArrayList<>() :
                                this.topics.stream()
                                        .map(StudentIssueEntity::toStudentIssue)
                                        .toList()
                )
                .build();
    }


}
