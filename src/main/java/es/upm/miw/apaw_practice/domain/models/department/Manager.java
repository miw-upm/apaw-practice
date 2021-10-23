package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public class Manager {
    private Integer experienceYears;
    private String phoneNumber;
    private String email;
    private List<DepartmentEmployee> departmentEmployees;

    public Manager() {
        //empty for framework
    }

    public static ManagerBuilder.Email builder() {
        return new Builder();
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
                ", departmentEmployees=" + departmentEmployees +
                '}';
    }

    public static class Builder implements ManagerBuilder.Email, ManagerBuilder.PhoneNumber, ManagerBuilder.ExperienceYears, ManagerBuilder.DepartmentEmployees, ManagerBuilder.Optionals {

        private final Manager manager;

        public Builder() {
            this.manager = new Manager();
        }

        @Override
        public ManagerBuilder.PhoneNumber email(String email) {
            this.manager.email = email;
            return this;
        }

        @Override
        public ManagerBuilder.ExperienceYears phoneNumber(String phoneNumber) {
            this.manager.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public ManagerBuilder.DepartmentEmployees experienceYears(Integer experienceYears) {
            this.manager.experienceYears = experienceYears;
            return this;
        }

        @Override
        public ManagerBuilder.Optionals departmentEmployees(List<DepartmentEmployee> departmentEmployeeList) {
            this.manager.departmentEmployees = departmentEmployeeList;
            return this;
        }

        @Override
        public Manager build() {
            return this.manager;
        }
    }
}
