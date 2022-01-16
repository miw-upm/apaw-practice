package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.models.training.CoursePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static java.math.RoundingMode.HALF_EVEN;

@Service
public class CourseService {
    private final CoursePersistence coursePersistence;

    @Autowired
    public CourseService(CoursePersistence coursePersistence) {
        this.coursePersistence = coursePersistence;
    }

    public Course create(Course course) {
        this.assertIdentityNotExist(course.getIdentity());
        return this.coursePersistence.create(course);
    }

    private void assertIdentityNotExist(String identity) {
        if (this.coursePersistence.existIdentity(identity)) {
            throw new ConflictException("Identity exist: " + identity);
        }
    }

    public void updatePrices(Stream<CoursePriceUpdating> coursePriceUpdatingList) {
        coursePriceUpdatingList.map(courseNewPrice -> {
            Course course = this.coursePersistence.read(courseNewPrice.getIdentity());
            course.setPrice(courseNewPrice.getPrice());
            return course;
        })
                .forEach(course -> this.coursePersistence.update(course.getIdentity(), course));
    }

    public double findCoursePriceSumByLecturerStartDate(LocalDate date) {
        BigDecimal result = this.coursePersistence.findCoursePriceSumByLecturerStartDate(date);
        return result.setScale(2, HALF_EVEN).doubleValue();
    }
}
