package es.upm.miw.apaw.adapters.mongodb.university.entities;

import es.upm.miw.apaw.domain.models.university.Teacher;
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
public class TeacherEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String identificationCode;
    private String specialization;
    private String fullName;
    private Boolean tenured;

    public TeacherEntity(UUID id, Teacher teacher) {
        this.id = id;
        this.identificationCode = teacher.getIdentificationCode();
        this.specialization = teacher.getSpecialization();
        this.fullName = teacher.getFullName();
        this.tenured = teacher.getTenured();
    }

    public Teacher toTeacher() {
        return Teacher.builder()
                .identificationCode(this.identificationCode)
                .specialization(this.specialization)
                .fullName(this.fullName)
                .tenured(this.tenured)
                .build();
    }
}
