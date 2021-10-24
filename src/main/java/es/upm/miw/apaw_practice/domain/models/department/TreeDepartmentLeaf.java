package es.upm.miw.apaw_practice.domain.models.department;

import java.math.BigDecimal;

public class TreeDepartmentLeaf implements TreeDepartment{
    private final Department department;

    public TreeDepartmentLeaf(Department department){
        this.department = department;
    }

    @Override
    public BigDecimal totalDepartmentManager() {
        return BigDecimal.valueOf(this.department.getManagers().size());
    }

    @Override
    public void add(TreeDepartment treeDepartment) {
        //Do nothing because is a leaf
    }
}
