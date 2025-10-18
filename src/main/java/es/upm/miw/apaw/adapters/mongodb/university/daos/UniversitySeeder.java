package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
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
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0104"))
                        .identificationCode("T005")
                        .specialization("TS005")
                        .fullName("TFN005")
                        .tenured(false)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0105"))
                        .identificationCode("T006")
                        .specialization("TS006")
                        .fullName("TFN006")
                        .tenured(true)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0106"))
                        .identificationCode("T007")
                        .specialization("TS007")
                        .fullName("TFN007")
                        .tenured(false)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0107"))
                        .identificationCode("T008")
                        .specialization("TS008")
                        .fullName("TFN008")
                        .tenured(true)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0108"))
                        .identificationCode("T009")
                        .specialization("TS009")
                        .fullName("TFN009")
                        .tenured(false)
                        .build(),
                TeacherEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0109"))
                        .identificationCode("T010")
                        .specialization("TS010")
                        .fullName("TFN010")
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
                        .build(),
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0204"))
                        .name("SN005")
                        .description("SD005")
                        .credits(6)
                        .build(),
                SubjectEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0205"))
                        .name("SN006")
                        .description("SD006")
                        .credits(6)
                        .build()
        };
        this.subjectRepository.saveAll(Arrays.asList(subjects));

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

        LessonEntity[] lessons5 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 19, 10, 0))
                        .classroom("D401")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 21, 10, 0))
                        .classroom("D401")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons6 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 20, 15, 0))
                        .classroom("E501")
                        .duration(120)
                        .build()
        };

        LessonEntity[] lessons7 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 22, 9, 0))
                        .classroom("F601")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 24, 9, 0))
                        .classroom("F601")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons8 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 23, 14, 0))
                        .classroom("G701")
                        .duration(60)
                        .build()
        };

        LessonEntity[] lessons9 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 25, 10, 0))
                        .classroom("H801")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 27, 10, 0))
                        .classroom("H801")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons10 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 26, 15, 0))
                        .classroom("I901")
                        .duration(120)
                        .build()
        };

        LessonEntity[] lessons11 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 28, 11, 0))
                        .classroom("J001")
                        .duration(60)
                        .build()
        };

        LessonEntity[] lessons12 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2024, 1, 29, 16, 0))
                        .classroom("K101")
                        .duration(90)
                        .build()
        };

        LessonEntity[] lessons13 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2026, 1, 29, 16, 0))
                        .classroom("K101")
                        .duration(60)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2026, 1, 29, 16, 0))
                        .classroom("K101")
                        .duration(60)
                        .build()
        };

        LessonEntity[] lessons14 = {
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2026, 1, 29, 16, 0))
                        .classroom("K101")
                        .duration(90)
                        .build(),
                LessonEntity.builder()
                        .startDate(LocalDateTime.of(2026, 2, 7, 16, 0))
                        .classroom("K101")
                        .duration(120)
                        .build()
        };

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
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0304"))
                        .capacity(28)
                        .subjectEntity(subjects[4])
                        .teacherEntities(Arrays.asList(teachers[4]))
                        .lessonEntities(Arrays.asList(lessons5))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0305"))
                        .capacity(22)
                        .subjectEntity(subjects[5])
                        .teacherEntities(Arrays.asList(teachers[5]))
                        .lessonEntities(Arrays.asList(lessons6))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0306"))
                        .capacity(40)
                        .subjectEntity(subjects[0])
                        .teacherEntities(Arrays.asList(teachers[1], teachers[2]))
                        .lessonEntities(Arrays.asList(lessons7))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0307"))
                        .capacity(35)
                        .subjectEntity(subjects[1])
                        .teacherEntities(Arrays.asList(teachers[3]))
                        .lessonEntities(Arrays.asList(lessons8))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0308"))
                        .capacity(45)
                        .subjectEntity(subjects[2])
                        .teacherEntities(Arrays.asList(teachers[4], teachers[5]))
                        .lessonEntities(Arrays.asList(lessons9))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0309"))
                        .capacity(38)
                        .subjectEntity(subjects[3])
                        .teacherEntities(Arrays.asList(teachers[0]))
                        .lessonEntities(Arrays.asList(lessons10))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0310"))
                        .capacity(42)
                        .subjectEntity(subjects[4])
                        .teacherEntities(Arrays.asList(teachers[1], teachers[2]))
                        .lessonEntities(Arrays.asList(lessons11))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0311"))
                        .capacity(48)
                        .subjectEntity(subjects[5])
                        .teacherEntities(Arrays.asList(teachers[3], teachers[4]))
                        .lessonEntities(Arrays.asList(lessons12))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0312"))
                        .capacity(48)
                        .subjectEntity(subjects[5])
                        .teacherEntities(Arrays.asList(teachers[8]))
                        .lessonEntities(Arrays.asList(lessons13))
                        .build(),
                SubjectAssignmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0313"))
                        .capacity(48)
                        .subjectEntity(subjects[5])
                        .teacherEntities(Arrays.asList(teachers[9]))
                        .lessonEntities(Arrays.asList(lessons14))
                        .build()
        };
        this.subjectAssignmentRepository.saveAll(Arrays.asList(subjectAssignments));

        EnrollmentEntity[] enrollments = {
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0400"))
                        .code("ENR001")
                        .semester("2024-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 10, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[1]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0401"))
                        .code("ENR002")
                        .semester("2024-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 11, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[3]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0402"))
                        .code("ENR003")
                        .semester("2024-2")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 12, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[1], subjectAssignments[2]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0403"))
                        .code("ENR004")
                        .semester("2024-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 13, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[2], subjectAssignments[3]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0404"))
                        .code("ENR005")
                        .semester("2024-2")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 14, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[2]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0410"))
                        .code("ENR100")
                        .semester("2025-2")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 14, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[2]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .build(),
                EnrollmentEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0411"))
                        .code("ENR101")
                        .semester("2025-1")
                        .enrollmentDate(LocalDateTime.of(2024, 1, 10, 14, 0))
                        .subjectAssignmentEntities(Arrays.asList(subjectAssignments[0], subjectAssignments[2]))
                        .studentId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
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
