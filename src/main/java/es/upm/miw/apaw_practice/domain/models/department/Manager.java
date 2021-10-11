package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public class Manager {
    private Integer experienceYears;
    private String phoneNumber;
    private String email;
    private List<Department> departments;
    private List<DepartmentEmployee> departmentEmployees;

    public Manager() {
        //empty for framework
    }

    public Manager(Integer experienceYears, String phoneNumber, String email, List<Department> departments, List<DepartmentEmployee> departmentEmployees) {
        this.experienceYears = experienceYears;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departments = departments;
        this.departmentEmployees = departmentEmployees;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<DepartmentEmployee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "experienceYears=" + experienceYears +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", departments=" + departments +
                ", departmentEmployees=" + departmentEmployees +
                '}';
    }
}
