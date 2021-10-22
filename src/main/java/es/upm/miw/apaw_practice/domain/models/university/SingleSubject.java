package es.upm.miw.apaw_practice.domain.models.university;

public class SingleSubject implements SubjectComponent{

    private final Subject subject;
    static final String UNSUPPORTED_LEAF_OPERATION = "Unsupported operation in leaf";

    public SingleSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean isArea() {
        return false;
    }

    @Override
    public void add(SubjectComponent subjectComponent) {
        throw new UnsupportedOperationException(UNSUPPORTED_LEAF_OPERATION);
    }

    @Override
    public void remove(SubjectComponent subjectComponent) {
        throw new UnsupportedOperationException(UNSUPPORTED_LEAF_OPERATION);
    }

    @Override
    public SubjectComponent get(Integer index) {
        throw new UnsupportedOperationException(UNSUPPORTED_LEAF_OPERATION);
    }

    @Override
    public String getDescription() {
        return this.subject.getTopic();
    }

}
