package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
import es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria;
import java.util.List;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import es.upm.miw.apaw.domain.ports.out.training.TrainingPlanGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingPlanService {

    private final TrainingPlanGateway trainingPlanGateway;
    private final CourseGateway courseGateway;
    private final UserFinder userFinder;

    public TrainingPlan create(CreationTrainingPlan creation) {
        if (this.trainingPlanGateway.existsByPlanCode(creation.getPlanCode())) {
            throw new ConflictException("TrainingPlan planCode already exists: " + creation.getPlanCode());
        }
        TrainingPlan trainingPlan = new TrainingPlan();
        BeanUtils.copyProperties(creation, trainingPlan);
        
        trainingPlan.setCourses(creation.getCourseIds().stream()
                .map(this::readCourse)
                .toList());
                
        trainingPlan.setUserSnapshots(this.userFinder.findByIds(new HashSet<>(creation.getUserIds())));
        
        trainingPlan.doDefault();
        return this.trainingPlanGateway.create(trainingPlan);
    }
    
    private Course readCourse(UUID courseId) {
        return this.courseGateway.read(courseId)
                .orElseThrow(() -> new NotFoundException("Course id not found: " + courseId));
    }
    
    public List<TrainingPlan> find(TrainingPlanFindCriteria criteria) {
        List<TrainingPlan> trainingPlans = this.trainingPlanGateway.find(criteria);
        if (trainingPlans.isEmpty()) {
            return List.of();
        }
        java.util.Set<UUID> userIds = trainingPlans.stream()
                .flatMap(plan -> plan.getUserSnapshots().stream())
                .map(es.upm.miw.apaw.domain.model.UserSnapshot::getId)
                .collect(java.util.stream.Collectors.toSet());
        return this.toSummaries(criteria, trainingPlans, this.userFinder.findByIds(userIds));
    }

    private List<TrainingPlan> toSummaries(
            TrainingPlanFindCriteria criteria,
            List<TrainingPlan> trainingPlans,
            List<es.upm.miw.apaw.domain.model.UserSnapshot> users) {
        java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById = users.stream()
                .collect(java.util.stream.Collectors.toMap(es.upm.miw.apaw.domain.model.UserSnapshot::getId, java.util.function.Function.identity()));
        return trainingPlans.stream()
                .peek(plan -> this.enrichUserSnapshots(plan, usersById))
                .filter(plan -> this.matchesUserFirstName(criteria, plan))
                .toList();
    }

    private void enrichUserSnapshots(TrainingPlan trainingPlan, java.util.Map<UUID, es.upm.miw.apaw.domain.model.UserSnapshot> usersById) {
        List<es.upm.miw.apaw.domain.model.UserSnapshot> enrichedUsers = trainingPlan.getUserSnapshots().stream()
                .map(user -> {
                    es.upm.miw.apaw.domain.model.UserSnapshot realUser = usersById.get(user.getId());
                    if (realUser == null) {
                        throw new NotFoundException("User id not found: " + user.getId());
                    }
                    return realUser;
                })
                .toList();
        trainingPlan.setUserSnapshots(enrichedUsers);
    }

        private boolean matchesUserFirstName(TrainingPlanFindCriteria criteria, TrainingPlan trainingPlan) {
        return !criteria.hasUserFirstName() || trainingPlan.getUserSnapshots().stream()
                .anyMatch(user -> criteria.getUserFirstName().equals(user.getFirstName()));
    }

}
