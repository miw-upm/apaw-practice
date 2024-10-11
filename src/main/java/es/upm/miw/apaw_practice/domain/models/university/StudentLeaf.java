package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.stream.Stream;

public class StudentLeaf extends StudentComponent {
    private final Student student;

    public StudentLeaf(Student student) {
        this.student = student;
    }

    @Override
    public void add(StudentComponent component) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public void remove(StudentComponent component) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public Stream<String> getEmails() {
        return Stream.of(student.getEmail());
    }

    @Override
    public Stream<String> getFirstNames() {
        return Stream.of(student.getFirstName());
    }

    @Override
    public Stream<String> getPlacesOfBirth() {
        return Stream.of(student.getPlaceOfBirth());
    }

    @Override
    public Stream<LocalDate> getEnrollmentDates() {
        return Stream.of(student.getEnrollmentDate());
    }

    @Override
    public Stream<Degree> getEnrolledDegrees() {
        return student.getEnrolledDegrees().stream();
    }

    public Student getStudent() {
        return student;
    }
}
