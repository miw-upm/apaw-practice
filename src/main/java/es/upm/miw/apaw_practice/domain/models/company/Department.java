package es.upm.miw.apaw_practice.domain.models.company;
import java.math.BigDecimal;
import java.util.List;

public class Department {
    private String department_name;// Primary Key
    private BigDecimal annualBudget;
    private int employeeCount;
    private Company company;
    private List<ExpenseBill> expenseBills;
    private List<Management> management;

    public Department() {
        //empty for framework
    }

    public Department(String department_name, BigDecimal annualBudget, int employeeCount, Company company) {
        this.department_name = department_name;
        this.annualBudget = annualBudget;
        this.employeeCount = employeeCount;
        this.company = company;


    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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


    public List<Management> getManagement() {
        return management;
    }

    public void setManagement(List<Management> management) {
        this.management = management;
    }

    public List<ExpenseBill> getExpenseBills() {
        return expenseBills;
    }

    public void setExpenseBills(List<ExpenseBill> expenseBills) {
        this.expenseBills = expenseBills;
    }


}

