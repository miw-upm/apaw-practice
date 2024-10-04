package es.upm.miw.apaw_practice.domain.models.company;
import java.math.BigDecimal;
import java.util.List;

public class Department {
    private String name;
    private BigDecimal annualBudget;
    private int employeeCount;
    private Company company;
    private List<Management> managementList;

    // Constructor, getters, and setters
    public Department(String name, BigDecimal annualBudget, int employeeCount, Company company) {
        this.name = name;
        this.annualBudget = annualBudget;
        this.employeeCount = employeeCount;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Management> getManagementList() {
        return managementList;
    }

    public void setManagementList(List<Management> managementList) {
        this.managementList = managementList;
    }
}

