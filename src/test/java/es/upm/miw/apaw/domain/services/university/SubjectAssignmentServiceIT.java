package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectAssignmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.SubjectAssignmentCapacityUpdating;
import es.upm.miw.apaw.domain.models.university.UserMobileSearching;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class SubjectAssignmentServiceIT {

    @Autowired
    private SubjectAssignmentService subjectAssignmentService;

    @Autowired
    private SubjectAssignmentRepository subjectAssignmentRepository;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testGetLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0304");

        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();

        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);

        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(2);

        Lesson lesson1 = lessons.get(0);
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 19, 10, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("D401");
        assertThat(lesson1.getDuration()).isEqualTo(90);

        Lesson lesson2 = lessons.get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 21, 10, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("D401");
        assertThat(lesson2.getDuration()).isEqualTo(90);
    }

    @Test
    void testGetLessonsNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");

        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(nonExistentId);
        assertThat(subjectAssignmentEntity).isEmpty();

        assertThatThrownBy(() -> subjectAssignmentService.getLessons(nonExistentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("SubjectAssignment id: " + nonExistentId);
    }

    @Test
    void testGetLessonsWithMultipleLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0305");

        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();

        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);

        assertThat(lessons).hasSize(1);

        Lesson lesson = lessons.getFirst();
        assertThat(lesson.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 20, 15, 0));
        assertThat(lesson.getClassroom()).isEqualTo("E501");
        assertThat(lesson.getDuration()).isEqualTo(120);
    }

    @Test
    void testGetLessonsWithSingleLesson() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300");

        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();

        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);

        assertThat(lessons).hasSize(2);
        Lesson lesson1 = lessons.getFirst();
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 15, 9, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("A101");
        assertThat(lesson1.getDuration()).isEqualTo(90);

        Lesson lesson2 = lessons.get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 17, 9, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("A101");
        assertThat(lesson2.getDuration()).isEqualTo(90);
    }

    @Test
    void testUpdateCapacities() {
        UUID subjectAssignmentId1 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0310");
        UUID subjectAssignmentId2 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0311");

        List<SubjectAssignmentCapacityUpdating> capacityUpdates = List.of(
                SubjectAssignmentCapacityUpdating.builder()
                        .id(subjectAssignmentId1)
                        .capacity(50)
                        .build(),
                SubjectAssignmentCapacityUpdating.builder()
                        .id(subjectAssignmentId2)
                        .capacity(75)
                        .build()
        );

        subjectAssignmentService.updateCapacities(capacityUpdates.stream());

        SubjectAssignmentEntity updatedEntity1 = subjectAssignmentRepository.findById(subjectAssignmentId1).orElseThrow();
        SubjectAssignmentEntity updatedEntity2 = subjectAssignmentRepository.findById(subjectAssignmentId2).orElseThrow();
        assertThat(updatedEntity1.getCapacity()).isEqualTo(50);
        assertThat(updatedEntity2.getCapacity()).isEqualTo(75);
    }

    @Test
    void testUpdateCapacitiesNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");

        List<SubjectAssignmentCapacityUpdating> capacityUpdates = List.of(
                SubjectAssignmentCapacityUpdating.builder()
                        .id(nonExistentId)
                        .capacity(50)
                        .build()
        );

        assertThatThrownBy(() -> subjectAssignmentService.updateCapacities(capacityUpdates.stream()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("SubjectAssignment id: " + nonExistentId);
    }

    @Test
    void testFindUniqueUsersMobilesByCapacity() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    String idSuffix = id.toString().substring(32);
                    return UserDto.builder()
                            .id(id)
                            .mobile("6" + idSuffix)
                            .firstName("Student" + idSuffix)
                            .build();
                });

        UserMobileSearching result = subjectAssignmentService.findUniqueUsersMobilesByCapacity(30);

        assertThat(result).isNotNull();
        assertThat(result.getMobiles()).isNotNull();
        assertThat(result.getMobiles()).hasSize(4);
        assertThat(result.getMobiles()).containsExactlyInAnyOrder("60001", "60003", "60004", "60005");
    }

    @Test
    void testFindUniqueUsersMobilesByCapacityWithDuplicates() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    String idSuffix = id.toString().substring(32);
                    return UserDto.builder()
                            .id(id)
                            .mobile("6" + idSuffix)
                            .firstName("Student" + idSuffix)
                            .build();
                });

        // capacity=20 tiene enrollments con students: 0002, 0003, 0004, 0005
        UserMobileSearching result = subjectAssignmentService.findUniqueUsersMobilesByCapacity(20);

        assertThat(result).isNotNull();
        assertThat(result.getMobiles()).isNotNull();
        assertThat(result.getMobiles()).hasSize(4);
        assertThat(result.getMobiles()).containsExactlyInAnyOrder("60002", "60003", "60004", "60005");
    }

    @Test
    void testFindUniqueUsersMobilesByCapacityNotFound() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    String idSuffix = id.toString().substring(32);
                    return UserDto.builder()
                            .id(id)
                            .mobile("6" + idSuffix)
                            .firstName("Student" + idSuffix)
                            .build();
                });

        UserMobileSearching result = subjectAssignmentService.findUniqueUsersMobilesByCapacity(999);

        assertThat(result).isNotNull();
        assertThat(result.getMobiles()).isNotNull();
        assertThat(result.getMobiles()).isEmpty();
    }

    @Test
    void testFindUniqueUsersMobilesByCapacityMultipleEnrollments() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    String idSuffix = id.toString().substring(32);
                    return UserDto.builder()
                            .id(id)
                            .mobile("6" + idSuffix)
                            .firstName("Student" + idSuffix)
                            .build();
                });

        UserMobileSearching result = subjectAssignmentService.findUniqueUsersMobilesByCapacity(25);

        assertThat(result).isNotNull();
        assertThat(result.getMobiles()).isNotNull();
        assertThat(result.getMobiles()).hasSize(2);
        assertThat(result.getMobiles()).containsExactlyInAnyOrder("60001", "60002");
    }
}
