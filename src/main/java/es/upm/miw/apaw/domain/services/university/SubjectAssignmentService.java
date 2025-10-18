package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.models.university.*;
import es.upm.miw.apaw.domain.persistenceports.university.EnrollmentPersistence;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectAssignmentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class SubjectAssignmentService {
    private final SubjectAssignmentPersistence subjectAssignmentPersistence;
    private final EnrollmentPersistence enrollmentPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public SubjectAssignmentService(SubjectAssignmentPersistence subjectAssignmentPersistence, EnrollmentPersistence enrollmentPersistence, UserRestClient userRestClient) {
        this.subjectAssignmentPersistence = subjectAssignmentPersistence;
        this.enrollmentPersistence = enrollmentPersistence;
        this.userRestClient = userRestClient;
    }

    public List<Lesson> getLessons(UUID subjectAssignmentId) {
        SubjectAssignment subjectAssignment = this.subjectAssignmentPersistence.getById(subjectAssignmentId);
        return subjectAssignment.getLessons();
    }

    public void updateCapacities(Stream<SubjectAssignmentCapacityUpdating> subjectAssignmentCapacityUpdatingList) {
        subjectAssignmentCapacityUpdatingList.map(capacityUpdating -> {
                    SubjectAssignment subjectAssignment = this.subjectAssignmentPersistence.getById(capacityUpdating.getId());
                    subjectAssignment.setCapacity(capacityUpdating.getCapacity());
                    return subjectAssignment;
                })
                .forEach(subjectAssignment -> this.subjectAssignmentPersistence.update(subjectAssignment.getId(), subjectAssignment));
    }

    public UserMobileSearching findUniqueUsersMobilesByCapacity(Integer capacity) {
        List<UUID> studentIds  = this.enrollmentPersistence.findAll()
                        .filter(enrollment -> enrollment.getSubjectAssignments().stream()
                                .anyMatch(subjectAssignment -> subjectAssignment.getCapacity().equals(capacity)))
                .map(enrollment -> enrollment.getStudent().getId())
                .toList();
        List<String> mobiles = studentIds.stream()
                .map(studentId -> userRestClient.readById(studentId).getMobile())
                .distinct()
                .toList();

        return new UserMobileSearching(mobiles);

    }
}
