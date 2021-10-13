package es.upm.miw.apaw_practice.adapters.mongodb.department.entities;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class DepartmentEntity {
    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private CompanyEntity companyEntity;
    @DBRef
    private List<ManagerEntity> managerEntities;

    public DepartmentEntity() {
        //empty for framework
    }

    public DepartmentEntity(String name, String description, CompanyEntity companyEntity, List<ManagerEntity> managerEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.companyEntity = companyEntity;
        this.managerEntities = managerEntities;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public List<ManagerEntity> getManagerEntities() {
        return managerEntities;
    }

    public void setManagerEntities(List<ManagerEntity> managerEntities) {
        this.managerEntities = managerEntities;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((DepartmentEntity) obj).id));
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", companyEntity=" + companyEntity +
                ", managerEntities=" + managerEntities +
                '}';
    }
}
