package es.upm.miw.apaw.adapters.mongodb.university.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class EnrollmentEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String code;
    private String semester;
    private LocalDateTime enrollmentDate;
    @DBRef
    private List<SubjectAssignmentEntity> subjectAssignmentEntities;
    private UUID studentId;
}
