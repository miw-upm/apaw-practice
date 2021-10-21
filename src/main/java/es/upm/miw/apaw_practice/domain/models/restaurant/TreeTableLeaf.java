package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.math.BigDecimal;

public class TreeTableLeaf implements TreeTable{
    private final Table table;

    public TreeTableLeaf(Table table) {
        this.table = table;
    }

    @Override
    public BigDecimal totalPrice() {
        return this.table.getPrice();
    }

    @Override
    public void add(TreeTable treeTable) {
        //Do nothing because is a leaf
    }
}
