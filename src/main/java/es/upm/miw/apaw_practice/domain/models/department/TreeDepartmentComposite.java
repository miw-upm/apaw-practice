package es.upm.miw.apaw_practice.domain.models.department;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TreeDepartmentComposite implements TreeDepartment {

    private final List<TreeDepartment> treeDepartmentList;

    public TreeDepartmentComposite() {
        this.treeDepartmentList = new ArrayList<>();
    }

    @Override
    public BigDecimal totalDepartmentManager() {
        return this.treeDepartmentList.stream()
                .map(TreeDepartment::totalDepartmentManager)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void add(TreeDepartment treeDepartment) {
        this.treeDepartmentList.add(treeDepartment);
    }
}
