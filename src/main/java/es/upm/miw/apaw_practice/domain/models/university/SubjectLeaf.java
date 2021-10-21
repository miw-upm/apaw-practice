package es.upm.miw.apaw_practice.domain.models.university;

public class SubjectLeaf implements SubjectComponent{

    private final Subject subject;

    public SubjectLeaf(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(SubjectComponent subjectComponent) {
        // Do nothing because it is a leaf
    }

    @Override
    public void remove(SubjectComponent subjectComponent) {
        // Do nothing because it is a leaf
    }

    @Override
    public String getDescription() {
        return this.subject.getTopic();
    }

}
