package es.upm.miw.apaw_practice.domain.models.course;

import java.util.Map;
import java.util.stream.Stream;

public abstract class CourseComponent {

    public abstract void add(CourseComponent courseComponent);

    public abstract void remove(CourseComponent courseComponent);

    public abstract boolean isComposite();

    public abstract Stream<String> getTitles();

    public abstract Stream<Boolean> getPaymentsRequired();

    public abstract Stream<Map<String, Boolean>> getCoursesPaymentsRequired();
}
