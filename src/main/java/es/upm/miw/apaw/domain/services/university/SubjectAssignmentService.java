package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import es.upm.miw.apaw.domain.models.university.SubjectAssignmentCapacityUpdating;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectAssignmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class SubjectAssignmentService {
    private final SubjectAssignmentPersistence subjectAssignmentPersistence;

    @Autowired
    public SubjectAssignmentService(SubjectAssignmentPersistence subjectAssignmentPersistence) {
        this.subjectAssignmentPersistence = subjectAssignmentPersistence;
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
}
