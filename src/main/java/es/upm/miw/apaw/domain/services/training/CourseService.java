package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CourseDurationUpdate;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        if (this.courseGateway.isReferenced(id)) {
            throw new ConflictException("Course is referenced by a training plan: " + id);
        }
        this.courseGateway.delete(id);
    }

    public List<Course> findAll() {
        return this.courseGateway.findAll();
    }

    @Transactional
    public void updateDurationHours(List<CourseDurationUpdate> updates) {
        this.assertUniqueIds(updates);
        List<Course> courses = updates.stream()
                .map(update -> {
                    Course course = this.read(update.id());
                    course.setDurationHours(update.durationHours());
                    return course;
                })
                .toList();
        courses.forEach(this.courseGateway::update);
    }

    private void assertUniqueIds(List<CourseDurationUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (CourseDurationUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated course id: " + update.id());
            }
        }
    }

    public java.util.List<es.upm.miw.apaw.domain.model.training.TrainingModalityReport> findModalityReport() {
        return this.courseGateway.findModalityReport();
    }
}