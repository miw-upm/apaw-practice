package es.upm.miw.apaw_practice.domain.models.university;

import java.util.List;

public class University {
    private String topDomain;
    private String name;
    private Boolean allowsInternationalStudents;
    private Integer numberOfFaculties;
    private List<Degree> degreesOffered;

    public University() {
        //empty for framework
    }

    public University(String topDomain, String name, boolean allowsInternationalStudents, int numberOfFaculties, List<Degree> degreesOffered) {
        this.topDomain = topDomain;
        this.name = name;
        this.allowsInternationalStudents = allowsInternationalStudents;
        this.numberOfFaculties = numberOfFaculties;
        this.degreesOffered = degreesOffered;
    }

    public String getTopDomain() {
        return topDomain;
    }

    public void setTopDomain(String topDomain) {
        this.topDomain = topDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAllowsInternationalStudents() {
        return allowsInternationalStudents;
    }

    public void setAllowsInternationalStudents(Boolean allowsInternationalStudents) {
        this.allowsInternationalStudents = allowsInternationalStudents;
    }

    public Integer getNumberOfFaculties() {
        return numberOfFaculties;
    }

    public void setNumberOfFaculties(Integer numberOfFaculties) {
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
                "topDomain='" + topDomain + '\'' +
                ", name='" + name + '\'' +
                ", allowsInternationalStudents=" + allowsInternationalStudents +
                ", numberOfFaculties=" + numberOfFaculties +
                ", degreesOffered=" + degreesOffered +
                '}';
    }
}
