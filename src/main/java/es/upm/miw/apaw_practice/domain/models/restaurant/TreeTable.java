package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.math.BigDecimal;

public interface TreeTable {
    BigDecimal totalPrice();

    void add(TreeTable treeTable);
}
