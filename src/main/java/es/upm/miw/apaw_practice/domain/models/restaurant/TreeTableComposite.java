package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TreeTableComposite implements TreeTable{
    private final List<TreeTable> treeTableList;

    public TreeTableComposite() {
        this.treeTableList = new ArrayList<>();
    }

    @Override
    public BigDecimal totalPrice() {
        return this.treeTableList.stream()
                .map(TreeTable::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void add(TreeTable treeTable) {
        this.treeTableList.add(treeTable);
    }
}
