package es.upm.miw.apaw_practice.domain.models.university;

public class SingleSubject implements SubjectComponent{

    private final Subject subject;

    public SingleSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean isArea() {
        return false;
    }

    @Override
    public void add(SubjectComponent subjectComponent) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public void remove(SubjectComponent subjectComponent) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public SubjectComponent get(Integer index) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public String getDescription() {
        return this.subject.getTopic();
    }

}
