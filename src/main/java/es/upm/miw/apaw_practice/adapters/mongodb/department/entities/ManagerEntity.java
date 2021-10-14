package es.upm.miw.apaw_practice.adapters.mongodb.department.entities;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class ManagerEntity {
    @Id
    private String id;
    private Integer experienceYears;
    private String phoneNumber;
    private String email;
    private List<DepartmentEmployeeEntity> departmentEmployeeEntities;

    public ManagerEntity() {
        //empty for framework
    }

    public ManagerEntity(Integer experienceYears, String phoneNumber, String email, List<DepartmentEmployeeEntity> departmentEmployeeEntities) {
        this.id = UUID.randomUUID().toString();
        this.experienceYears = experienceYears;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departmentEmployeeEntities = departmentEmployeeEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<DepartmentEmployeeEntity> getDepartmentEmployeeEntities() {
        return departmentEmployeeEntities;
    }

    public void setDepartmentEmployeeEntities(List<DepartmentEmployeeEntity> departmentEmployeeEntities) {
        this.departmentEmployeeEntities = departmentEmployeeEntities;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ManagerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "ManagerEntity{" +
                "id='" + id + '\'' +
                ", experienceYears=" + experienceYears +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", departmentEmployeeEntities=" + departmentEmployeeEntities +
                '}';
    }
}
