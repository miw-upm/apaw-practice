package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;

public interface DepartmentBuilders {


    interface DepartmentName {
        AnnualBudget departmentName(String departmentName);
    }


    interface AnnualBudget {
        EmployeeCount annualBudget(BigDecimal annualBudget);
    }


    interface EmployeeCount {
        Management employeeCount(int employeeCount);
    }


    interface Management {
        Optionals management(es.upm.miw.apaw_practice.domain.models.company.Management management);

        Optionals management(Management management);
    }

    // 可选步骤：可添加更多可选字段
    interface Optionals {
        // 可选字段可以在这里继续扩展

        // 最终构建方法
        Department build();
    }
}