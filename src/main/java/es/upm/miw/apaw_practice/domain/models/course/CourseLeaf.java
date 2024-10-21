package es.upm.miw.apaw_practice.domain.models.course;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CourseLeaf extends CourseComponent{

    private final Course course;

    public CourseLeaf(Course course) {
        this.course = course;
    }

    @Override
    public void add(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public void remove(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public Stream<String> getTitles() {
        return Stream.of(this.course.getTitle());
    }

    @Override
    public Stream<Boolean> getPaymentsRequired() {
        return Stream.of(this.course.getPaymentRequired());
    }

    @Override
    public Stream<Map<String, Boolean>> getCoursesPaymentsRequired() {
        Map<String, Boolean> courseInfo = new HashMap<>();
        courseInfo.put(this.course.getTitle(), this.course.getPaymentRequired());
        return Stream.of(courseInfo);
    }
}
