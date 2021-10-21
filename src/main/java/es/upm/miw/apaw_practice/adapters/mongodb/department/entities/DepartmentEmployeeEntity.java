package es.upm.miw.apaw_practice.adapters.mongodb.department.entities;

import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class DepartmentEmployeeEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private LocalDate birthday;
    private Boolean isActive;

    public DepartmentEmployeeEntity() {
        //empty for framework
    }

    public DepartmentEmployeeEntity(DepartmentEmployee departmentEmployee) {
        BeanUtils.copyProperties(departmentEmployee, this);
        this.id = UUID.randomUUID().toString();
    }

    public DepartmentEmployeeEntity(String dni, LocalDate birthday, Boolean isActive) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.birthday = birthday;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((DepartmentEmployeeEntity) obj).id));
    }

    @Override
    public String toString() {
        return "DepartmentEmployeeEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", birthday=" + birthday +
                ", isActive=" + isActive +
                '}';
    }

    public DepartmentEmployee toDepartmentEmployee() {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        BeanUtils.copyProperties(this, departmentEmployee);
        return departmentEmployee;
    }

    public void fromDepartmentEmployee(DepartmentEmployee departmentEmployee) {
        BeanUtils.copyProperties(departmentEmployee, this);
    }
}
