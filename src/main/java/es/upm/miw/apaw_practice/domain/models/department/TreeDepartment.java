package es.upm.miw.apaw_practice.domain.models.department;

import java.math.BigDecimal;

public interface TreeDepartment {
    BigDecimal totalDepartmentManager();

    void add(TreeDepartment treeDepartment);

}