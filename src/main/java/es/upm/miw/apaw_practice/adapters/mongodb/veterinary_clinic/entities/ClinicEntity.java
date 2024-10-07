package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class ClinicEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String address;
    @DBRef
    private List<EmployeeEntity> employeeEntities;

    public ClinicEntity() {
        //empty from framework
    }

    public ClinicEntity(String name, String address, List<EmployeeEntity> employeeEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.employeeEntities = employeeEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }

    public Clinic toClinic() {
        List<Employee> employees = this.employeeEntities.stream()
                .map(EmployeeEntity::toEmployee)
                .collect(Collectors.toList());
        return new Clinic(name, address, employees);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object != null && getClass() == object.getClass() &&
                (name.equals(((ClinicEntity) object).name));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}