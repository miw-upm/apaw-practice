package es.upm.miw.apaw_practice.adapters.mongodb.department.entities;

import es.upm.miw.apaw_practice.domain.models.department.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

public class CompanyEntity {
    @Id
    private String id;
    private String direction;
    @Indexed(unique = true)
    private String cif;

    public CompanyEntity() {
        //empty for framework
    }

    public CompanyEntity(String direction, String cif) {
        this.id = UUID.randomUUID().toString();
        this.direction = direction;
        this.cif = cif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CompanyEntity) obj).id));
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", cif='" + cif + '\'' +
                '}';
    }

    public void fromCompany(Company company) {
        BeanUtils.copyProperties(company, this);
    }

    public Company toCompany() {
        Company company = new Company();
        BeanUtils.copyProperties(this, company);
        return company;
    }
}
