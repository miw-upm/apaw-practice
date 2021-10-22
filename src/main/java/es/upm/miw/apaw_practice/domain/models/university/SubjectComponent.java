package es.upm.miw.apaw_practice.domain.models.university;

public interface SubjectComponent {

    boolean isArea();

    void add(SubjectComponent subjectComponent);

    void remove(SubjectComponent subjectComponent);

    SubjectComponent get(Integer index);

    String getDescription();

}
