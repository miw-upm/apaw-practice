package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.stream.Stream;

public abstract class StudentComponent {
    public abstract Stream<String> getEmails();

    public abstract Stream<String> getFirstNames();

    public abstract Stream<String> getPlacesOfBirth();

    public abstract Stream<LocalDate> getEnrollmentDates();

    public abstract Stream<Degree> getEnrolledDegrees();
}
