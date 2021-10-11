package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public class Company {
    private String direction;
    private String cif;
    private List<Department> departments;

    public Company() {
        //empty for framework
    }

    public Company(String direction, String cif, List<Department> departments) {
        this.direction = direction;
        this.cif = cif;
        this.departments = departments;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "direction='" + direction + '\'' +
                ", cif='" + cif + '\'' +
                ", departments=" + departments +
                '}';
    }
}
