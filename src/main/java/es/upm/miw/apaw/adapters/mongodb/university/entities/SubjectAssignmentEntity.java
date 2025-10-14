package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import es.upm.miw.apaw.domain.models.university.Teacher;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        BeanUtils.copyProperties(this, subjectAssignment);
        subjectAssignment.setSubject(this.subjectEntity.toSubject());
        subjectAssignment.setTeachers(this.teacherEntities.stream()
                .map(TeacherEntity::toTeacher)
                .collect(Collectors.toList()));
        subjectAssignment.setLessons(this.lessonEntities.stream()
                .map(LessonEntity::toLesson)
                .collect(Collectors.toList()));
        return subjectAssignment;
    }
}
