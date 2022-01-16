package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Repository
public interface CoursePersistence {

    Course read(String identity);

    boolean existIdentity(String identity);

    Stream<Course> readAll();

    Course create(Course course);

    Course update(String identity, Course course);

    BigDecimal findCoursePriceSumByLecturerStartDate(LocalDate date);
}
