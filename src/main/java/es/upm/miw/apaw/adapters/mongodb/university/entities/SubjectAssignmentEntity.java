package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
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
    private SubjectEntity subjectEntity;
    private List<TeacherEntity> teacherEntities;
    private List<LessonEntity> lessonEntities;
}
