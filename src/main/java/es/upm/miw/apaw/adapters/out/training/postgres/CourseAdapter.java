package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseAdapter implements CourseGateway {
    private final CourseRepository courseRepository;

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
}
