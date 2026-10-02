package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseAdapter implements CourseGateway {
    private final CourseRepository courseRepository;
    private final TrainingPlanRepository trainingPlanRepository;

    @Override
    public Course create(Course course) {
        CourseEntity entity = new CourseEntity(course);
        return this.courseRepository.save(entity).toDomain();
    }

    @Override
    public boolean existsByName(String name) {
        return this.courseRepository.existsByName(name);
    }

    @Override
    public Optional<Course> read(UUID id) {
        return this.courseRepository.findById(id)
                .map(CourseEntity::toDomain);
    }

    @Override
    public Course update(Course course) {
        CourseEntity entity = new CourseEntity(course);
        return this.courseRepository.save(entity).toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.trainingPlanRepository.existsByCoursesId(id);
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAllByOrderByNameAsc().stream()
                .map(CourseEntity::toDomain)
                .toList();
    }

    @Override
    public java.util.List<es.upm.miw.apaw.domain.model.training.TrainingModalityReport> findModalityReport() {
        return this.trainingPlanRepository.findTrainingModalityReport();
    }
}