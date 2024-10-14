package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Document
public class CompanyEntity {

    @Id
    @Indexed(unique = true)
    private String companyname; // Primary Key

    private String location;
    private String industry;
    private LocalDate creationDate;

    @DBRef
    private List<DepartmentEntity> departmentEntities;

    public CompanyEntity() {
        // Empty for framework
    }

    public CompanyEntity(String companyname, String location, String industry, LocalDate creationDate, List<DepartmentEntity> departmentEntities) {
        this.companyname = companyname;
        this.location = location;
        this.industry = industry;
        this.creationDate = creationDate;
        this.departmentEntities = departmentEntities;
    }




    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<DepartmentEntity> getDepartmentEntities() {
        return departmentEntities;
    }

    public void setDepartmentEntities(List<DepartmentEntity> departmentEntities) {
        this.departmentEntities = departmentEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(companyname, that.companyname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyname);
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "companyname='" + companyname + '\'' +
                ", location='" + location + '\'' +
                ", industry='" + industry + '\'' +
                ", creationDate=" + creationDate +
                ", departmentEntities=" + departmentEntities +
                '}';
    }
}
