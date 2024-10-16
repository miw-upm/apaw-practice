package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Document
public class DepartmentEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String departmentName;
    private BigDecimal annualBudget;
    private int employeeCount;
    @DBRef
    private ManagementEntity managementEntity; // Assuming a relationship with management

    public DepartmentEntity() {
        // Empty for framework
    }

    public DepartmentEntity(String departmentName,BigDecimal annualBudget,int employeeCount, ManagementEntity managementEntity) {
        this.id = UUID.randomUUID().toString();
        this.departmentName = departmentName;
        this.annualBudget = annualBudget;
        this.employeeCount = employeeCount;
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
    public BigDecimal getAnnualBudget() {
        return annualBudget;
    }

    public void setAnnualBudget(BigDecimal annualBudget) {
        this.annualBudget = annualBudget;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public ManagementEntity getManagementEntity() {
        return managementEntity;
    }

    public void setManagementEntity(ManagementEntity managementEntity) {
        this.managementEntity = managementEntity;
    }

    public Department toDepartment() {
        Department department = new Department();
        BeanUtils.copyProperties(this, department);
        return department;
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
                ", annualBudget=" + annualBudget +
                ", employeeCount=" + employeeCount +
                ", managementEntity=" + managementEntity +
                '}';
    }
}
