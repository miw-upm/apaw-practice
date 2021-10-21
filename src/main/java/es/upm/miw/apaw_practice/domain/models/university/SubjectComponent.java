package es.upm.miw.apaw_practice.domain.models.university;

public interface SubjectComponent {

    boolean isComposite();

    void add(SubjectComponent subjectComponent);

    void remove(SubjectComponent subjectComponent);

    String getDescription();

}
