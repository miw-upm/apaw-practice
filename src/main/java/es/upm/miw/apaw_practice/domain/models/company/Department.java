package es.upm.miw.apaw_practice.domain.models.company;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

public class Department {
    @Indexed(unique = true)
    private String departmentName;// Primary Key
    private BigDecimal annualBudget;
    private int employeeCount;
    private Management management;

    public Department() {
        //empty for framework
    }

    public Department(String departmentName, BigDecimal annualBudget, int employeeCount, Management management) {
        this.departmentName = departmentName;
        this.annualBudget = annualBudget;
        this.employeeCount = employeeCount;
        this.management = management;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

}

