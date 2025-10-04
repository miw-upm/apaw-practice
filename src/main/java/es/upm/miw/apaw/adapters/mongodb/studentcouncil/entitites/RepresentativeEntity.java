package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;

import es.upm.miw.apaw.domain.models.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private UserDto representative;
    private List<StudentIssueEntity> topics;
}
