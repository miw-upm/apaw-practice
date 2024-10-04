package es.upm.miw.apaw_practice.domain.models.company;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private String name;
    private String location;
    private String industry;
    private LocalDate creationDate;
    private List<Department> departments;
    private List<ExpenseBill> expenseBills;

    // Constructor, getters, and setters
    public Company(String name, String location, String industry, LocalDate creationDate) {
        this.name = name;
        this.location = location;
        this.industry = industry;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ExpenseBill> getExpenseBills() {
        return expenseBills;
    }

    public void setExpenseBills(List<ExpenseBill> expenseBills) {
        this.expenseBills = expenseBills;
    }
}
