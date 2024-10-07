package es.upm.miw.apaw_practice.domain.models.company;
import java.math.BigDecimal;
import java.util.List;

public class Department {
    private String departmentname;// Primary Key
    private BigDecimal annualBudget;
    private int employeeCount;
    private Company company;
    private Management management;

    public Department() {
        //empty for framework
    }

    public Department(String departmentname, BigDecimal annualBudget, int employeeCount, Company company,Management management) {
        this.departmentname = departmentname;
        this.annualBudget = annualBudget;
        this.employeeCount = employeeCount;
        this.company = company;
        this.management = management;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
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


    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

}

