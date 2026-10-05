package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.ports.out.training.TrainingPlanGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria;
import org.springframework.data.jpa.domain.Specification;
import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainingPlanAdapter implements TrainingPlanGateway {

    private final TrainingPlanRepository trainingPlanRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public TrainingPlan create(TrainingPlan trainingPlan) {
        TrainingPlanEntity trainingPlanEntity = new TrainingPlanEntity(trainingPlan);
        List<CourseEntity> courseEntities = trainingPlan.getCourses().stream()
                .map(course -> this.courseRepository.getReferenceById(course.getId()))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        trainingPlanEntity.setCourses(courseEntities);
        this.trainingPlanRepository.save(trainingPlanEntity);
        return trainingPlan;
    }
    
    @Override
    public boolean existsByPlanCode(String planCode) {
        return this.trainingPlanRepository.existsByPlanCode(planCode);
    }
    
    @Override
    public List<TrainingPlan> find(TrainingPlanFindCriteria criteria) {
        Specification<TrainingPlanEntity> specification = this.buildSpecification(criteria);
        return this.trainingPlanRepository.findAll(specification).stream()
                .map(this::toDomainWithoutCourses)
                .toList();
    }

    private TrainingPlan toDomainWithoutCourses(TrainingPlanEntity entity) {
        TrainingPlan trainingPlan = new TrainingPlan();
        org.springframework.beans.BeanUtils.copyProperties(entity, trainingPlan, "courses", "userIds");
        trainingPlan.setCourses(List.of());
        trainingPlan.setUserSnapshots(entity.getUserIds().stream()
                .map(id -> UserSnapshot.builder().id(id).build())
                .toList());
        return trainingPlan;
    }

    private Specification<TrainingPlanEntity> buildSpecification(TrainingPlanFindCriteria criteria) {
        Specification<TrainingPlanEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasEvaluationScore()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("evaluationScore"), criteria.getEvaluationScore()));
        }
        specification = this.addCourseName(specification, criteria.getCourseName());
        return specification;
    }

    private Specification<TrainingPlanEntity> addCourseName(
            Specification<TrainingPlanEntity> specification, String courseName) {
        if (courseName == null || courseName.isBlank()) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("courses").get("name"), courseName);
        });
    }
}
