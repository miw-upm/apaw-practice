package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;
import java.util.UUID;

@Document
public class DepartmentEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String departmentName;

    private String location;

    @DBRef
    private ManagementEntity managementEntity; // Assuming a relationship with management

    public DepartmentEntity() {
        // Empty for framework
    }

    public DepartmentEntity(String departmentName, String location, ManagementEntity managementEntity) {
        this.id = UUID.randomUUID().toString();
        this.departmentName = departmentName;
        this.location = location;
        this.managementEntity = managementEntity;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ManagementEntity getManagementEntity() {
        return managementEntity;
    }

    public void setManagementEntity(ManagementEntity managementEntity) {
        this.managementEntity = managementEntity;
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName);
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id='" + id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", location='" + location + '\'' +
                ", managementEntity=" + managementEntity +
                '}';
    }
}
