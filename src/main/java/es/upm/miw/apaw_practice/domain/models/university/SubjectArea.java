package es.upm.miw.apaw_practice.domain.models.university;

import java.util.ArrayList;
import java.util.List;

public class SubjectArea implements SubjectComponent{

    private String area;
    private final List<SubjectComponent> subjectComponents;

    public SubjectArea(String area) {
        this.area = area;
        this.subjectComponents = new ArrayList<>();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(SubjectComponent subjectComponent) {
        this.subjectComponents.add(subjectComponent);
    }

    @Override
    public void remove(SubjectComponent subjectComponent) {
        this.subjectComponents.remove(subjectComponent);
    }

    @Override
    public String getDescription() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
