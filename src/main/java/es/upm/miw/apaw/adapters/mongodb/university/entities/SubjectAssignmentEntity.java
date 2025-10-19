package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import lombok.*;
import org.springframework.beans.BeanUtils;
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


    public SubjectAssignment toSubjectAssignment() {
        SubjectAssignment subjectAssignment = new SubjectAssignment();
        BeanUtils.copyProperties(this, subjectAssignment, "teacherEntities", "lessonEntities");
        subjectAssignment.setSubject(this.subjectEntity.toSubject());
        subjectAssignment.setTeachers(this.teacherEntities.stream()
                .map(TeacherEntity::toTeacher)
                .toList());
        subjectAssignment.setLessons(this.lessonEntities.stream()
                .map(LessonEntity::toLesson)
                .toList());
        return subjectAssignment;
    }
}
