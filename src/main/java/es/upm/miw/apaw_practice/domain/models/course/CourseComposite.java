package es.upm.miw.apaw_practice.domain.models.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CourseComposite extends CourseComponent{

    private final List<CourseComponent> components;

    public CourseComposite(){
        this(new ArrayList<>());
    }

    public CourseComposite(List<CourseComponent> components) {
        this.components = components;
    }


    @Override
    public void add(CourseComponent courseComponent) {
        this.components.add(courseComponent);
    }

    @Override
    public void remove(CourseComponent courseComponent) {
        this.components.remove(courseComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public Stream<String> getTitles() {
        return this.components
                .stream()
                .flatMap(CourseComponent::getTitles);
    }

    @Override
    public Stream<Boolean> getPaymentsRequired() {
        return components
                .stream()
                .flatMap(CourseComponent::getPaymentsRequired);
    }

    @Override
    public Stream<Map<String, Boolean>> getCoursesPaymentsRequired() {
        return this.components
                .stream()
                .map(courseComponent -> {
                        Map<String, Boolean> courseMap = new HashMap<>();
                        courseMap.put(courseComponent.getTitles().findAny().get(), courseComponent.getPaymentsRequired().findFirst().get());
                        return courseMap;
                });
    }
}
