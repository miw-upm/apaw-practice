package es.upm.miw.apaw.adapters.mongodb.university.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class SubjectAssignmentEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private Integer capacity;
    @DBRef
    private SubjectEntity subjectEntity;
    @DBRef
    private List<TeacherEntity> teacherEntities;
    private List<LessonEntity> lessonEntities;
}
