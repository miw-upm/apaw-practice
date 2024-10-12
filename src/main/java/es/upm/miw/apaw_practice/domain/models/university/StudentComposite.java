package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StudentComposite extends StudentComponent {
    private final List<StudentComponent> components;

    public StudentComposite() {
        this(new ArrayList<>());
    }

    public StudentComposite(List<StudentComponent> components) {
        this.components = components;
    }

    @Override
    public void add(StudentComponent component) {
        components.add(component);
    }

    @Override
    public void remove(StudentComponent component) {
        components.remove(component);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public Stream<String> getEmails() {
        return components
                .stream()
                .flatMap(StudentComponent::getEmails);
    }

    @Override
    public Stream<String> getFirstNames() {
        return components
                .stream()
                .flatMap(StudentComponent::getFirstNames);
    }

    @Override
    public Stream<String> getPlacesOfBirth() {
        return components
                .stream()
                .flatMap(StudentComponent::getPlacesOfBirth);
    }

    @Override
    public Stream<LocalDate> getEnrollmentDates() {
        return components
                .stream()
                .flatMap(StudentComponent::getEnrollmentDates);
    }

    @Override
    public Stream<Degree> getEnrolledDegrees() {
        return components
                .stream()
                .flatMap(StudentComponent::getEnrolledDegrees);
    }

    public List<StudentComponent> getComponents() {
        return components;
    }
}
