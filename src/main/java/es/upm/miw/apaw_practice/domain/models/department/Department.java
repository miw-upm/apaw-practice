package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public class Department {
    private String name;
    private String description;
    private Company company;
    private List<Manager> managers;

    public Department() {
        //empty for framework
    }

    public Department(String name, String description, Company company, List<Manager> managers) {
        this.name = name;
        this.description = description;
        this.company = company;
        this.managers = managers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company +
                ", managers=" + managers +
                '}';
    }
}
