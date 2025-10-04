package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class UniversitySeeder {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectAssignmentRepository subjectAssignmentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public UniversitySeeder(TeacherRepository teacherRepository, SubjectRepository subjectRepository,
                            SubjectAssignmentRepository subjectAssignmentRepository, EnrollmentRepository enrollmentRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.subjectAssignmentRepository = subjectAssignmentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void seedDatabase() {
        log.warn("------- University Initial Load -----------");

        // 1) Teachers
        TeacherEntity[] teachers = {
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                        .identificationCode("T001")
                        .specialization("TS001")
                        .fullName("TFN001")
                        .tenured(true)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101"))
                        .identificationCode("T002")
                        .specialization("TS002")
                        .fullName("TFN002")
                        .tenured(true)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0102"))
                        .identificationCode("T003")
                        .specialization("TS003")
                        .fullName("TFN003")
                        .tenured(false)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0103"))
                        .identificationCode("T004")
                        .specialization("TS004")
                        .fullName("TFN004")
                        .tenured(true)
                        .build()
        };
        this.teacherRepository.saveAll(Arrays.asList(teachers));

        SubjectEntity[] subjects = {
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200"))
                        .name("SN001")
                        .description("SD001")
                        .credits(6)
                        .build(),
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0201"))
                        .name("SN002")
                        .description("SD002")
                        .credits(6)
                        .build(),
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0202"))
                        .name("SN003")
                        .description("SD003")
                        .credits(6)
                        .build(),
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0203"))
                        .name("SN004")
                        .description("SD004")
                        .credits(6)
                        .build()
        };
        this.subjectRepository.saveAll(Arrays.asList(subjects));

        // 3) Lessons (as embedded objects)
        LessonEntity[] lessons1 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 15, 9, 0))
                        .classroom("A101")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 17, 9, 0))
                        .classroom("A101")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons2 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 16, 11, 0))
                        .classroom("B201")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 18, 11, 0))
                        .classroom("B201")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons3 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 15, 14, 0))
                        .classroom("C301")
                        .duration(60)
                        .build()
        };

        LessonEntity[] lessons4 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 16, 16, 0))
                        .classroom("A102")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 18, 16, 0))
                        .classroom("A102")
                        .duration(60)
                        .build()
        };

        // 4) Subject Assignments
        SubjectAssignmentEntity[] subjectAssignments = {
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300"))
                        .capacity(30)
                        .subjectEntity(subjects[0])
                        .teacherEntities(Arrays.asList(teachers[0], teachers[3]))
                        .lessonEntities(Arrays.asList(lessons1))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0301"))
                        .capacity(25)
                        .subjectEntity(subjects[1])
                        .teacherEntities(Arrays.asList(teachers[1]))
                        .lessonEntities(Arrays.asList(lessons2))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0302"))
                        .capacity(20)
                        .subjectEntity(subjects[2])
                        .teacherEntities(Arrays.asList(teachers[2]))
                        .lessonEntities(Arrays.asList(lessons3))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0303"))
                        .capacity(35)
                        .subjectEntity(subjects[3])
                        .teacherEntities(Arrays.asList(teachers[0]))
                        .lessonEntities(Arrays.asList(lessons4))
                        .build()
        };
        this.subjectAssignmentRepository.saveAll(Arrays.asList(subjectAssignments));

        // 5) Enrollments
        EnrollmentEntity[] enrollments = {
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0400"))
                        .code("ENR001")
                        .semester("2024-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 10, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[1]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0500"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0401"))
                        .code("ENR002")
                        .semester("2024-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 11, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[3]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0501"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0402"))
                        .code("ENR003")
                        .semester("2024-2")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 12, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[1], subjectAssignments[2]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0502"))
                        .build()
        };
        this.enrollmentRepository.saveAll(Arrays.asList(enrollments));

        log.warn("        ------- university");
    }

    public void deleteAll() {
        this.enrollmentRepository.deleteAll();
        this.subjectAssignmentRepository.deleteAll();
        this.subjectRepository.deleteAll();
        this.teacherRepository.deleteAll();
    }
}
