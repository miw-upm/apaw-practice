package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
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
}


