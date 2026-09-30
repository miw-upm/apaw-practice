package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import org.springframework.stereotype.Repository;

@Repository
public class CourseAdapter implements CourseGateway {
    private final CourseRepository courseRepository;

    public CourseAdapter(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(Course course) {
        CourseEntity entity = new CourseEntity();
        entity.setId(course.getId());
        entity.setName(course.getName());
        entity.setCertificateReference(course.getCertificateReference());
        entity.setDurationHours(course.getDurationHours());
        entity.setOnline(course.getOnline());
        entity.setLaunchDate(course.getLaunchDate());
        
        return this.courseRepository.save(entity).toDomain();
    }
}
