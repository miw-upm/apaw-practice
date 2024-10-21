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

    public static DepartmentBuilders.DepartmentName builder() {
        return new Builder();
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
    // Builder 类
    public static class Builder implements DepartmentBuilders.DepartmentName,
            DepartmentBuilders.AnnualBudget,
            DepartmentBuilders.EmployeeCount,
            DepartmentBuilders.Management,
            DepartmentBuilders.Optionals {

        private final Department department;

        public Builder() {
            this.department = new Department();
        }

        @Override
        public DepartmentBuilders.AnnualBudget departmentName(String departmentName) {
            this.department.departmentName = departmentName;
            return this;
        }

        @Override
        public DepartmentBuilders.EmployeeCount annualBudget(BigDecimal annualBudget) {
            this.department.annualBudget = annualBudget;
            return this;
        }

        @Override
        public DepartmentBuilders.Management employeeCount(int employeeCount) {
            this.department.employeeCount = employeeCount;
            return this;
        }

        @Override
        public DepartmentBuilders.Optionals management(Management management) {
            this.department.management = management;
            return this;
        }

        @Override
        public Department build() {
            return this.department;
        }

        @Override
        public DepartmentBuilders.Optionals management(DepartmentBuilders.Management management) {
            return null;
        }
    }

}

