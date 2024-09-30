package es.upm.miw.apaw_practice.domain.models.university;

import java.util.List;

public class University {
    private String name;
    private boolean allowsInternationalStudents;
    private int numberOfFaculties;
    private List<Degree> degreesOffered;

    public University() {
        //empty for framework
    }

    public University(String name, boolean allowsInternationalStudents, int numberOfFaculties, List<Degree> degreesOffered) {
        this.name = name;
        this.allowsInternationalStudents = allowsInternationalStudents;
        this.numberOfFaculties = numberOfFaculties;
        this.degreesOffered = degreesOffered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowsInternationalStudents() {
        return allowsInternationalStudents;
    }

    public void setAllowsInternationalStudents(boolean allowsInternationalStudents) {
        this.allowsInternationalStudents = allowsInternationalStudents;
    }

    public int getNumberOfFaculties() {
        return numberOfFaculties;
    }

    public void setNumberOfFaculties(int numberOfFaculties) {
        this.numberOfFaculties = numberOfFaculties;
    }

    public List<Degree> getDegreesOffered() {
        return degreesOffered;
    }

    public void setDegreesOffered(List<Degree> degreesOffered) {
        this.degreesOffered = degreesOffered;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", allowsInternationalStudents=" + allowsInternationalStudents +
                ", numberOfFaculties=" + numberOfFaculties +
                ", degreesOffered=" + degreesOffered +
                '}';
    }
}
