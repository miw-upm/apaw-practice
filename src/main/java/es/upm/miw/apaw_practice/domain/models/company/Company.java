package es.upm.miw.apaw_practice.domain.models.company;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private String company_name;// Primary Key
    private String location;
    private String industry;
    private LocalDate creationDate;
    private List<Department> departments;

    public Company() {
        //empty for framework
    }

    public Company(String company_name, String location, String industry, LocalDate creationDate) {
        this.company_name = company_name;
        this.location = location;
        this.industry = industry;
        this.creationDate = creationDate;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }




}
