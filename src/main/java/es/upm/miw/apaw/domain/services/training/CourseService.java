package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseGateway courseGateway;

    public Course create(Course course) {
        if (this.courseGateway.existsByName(course.getName())) {
            throw new ConflictException("Course name already exists: " + course.getName());
        }
        course.doDefault();
        return this.courseGateway.create(course);
    }

    public Course read(UUID id) {
        return this.courseGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Course id not found: " + id));
    }

    public Course update(UUID id, Course course) {
        Course storedCourse = this.read(id);
        
        if (!storedCourse.getName().equals(course.getName())
                && this.courseGateway.existsByName(course.getName())) {
            throw new ConflictException("Course name already exists: " + course.getName());
        }
        
        storedCourse.setName(course.getName());
        storedCourse.setCertificateReference(course.getCertificateReference());
        storedCourse.setDurationHours(course.getDurationHours());
        storedCourse.setOnline(course.getOnline());
        storedCourse.setLaunchDate(course.getLaunchDate());
        
        return this.courseGateway.update(storedCourse);
    }

    public void delete(UUID id) {
        this.courseGateway.delete(id);
    }
}
