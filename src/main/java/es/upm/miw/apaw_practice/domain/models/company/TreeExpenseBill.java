package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;

public interface TreeExpenseBill {


    String getDescription();


    BigDecimal getAmount();


    void add(TreeExpenseBill treeExpenseBill);


    void remove(TreeExpenseBill treeExpenseBill);


    String print();
}
