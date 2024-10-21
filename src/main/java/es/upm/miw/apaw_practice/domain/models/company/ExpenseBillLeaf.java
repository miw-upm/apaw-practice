package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseBillLeaf implements TreeExpenseBill {

    private String description;
    private BigDecimal amount;
    private LocalDateTime date;

    public ExpenseBillLeaf(String description, BigDecimal amount, LocalDateTime date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public void add(TreeExpenseBill treeExpenseBill) {
        throw new UnsupportedOperationException("Cannot add to a leaf node");
    }

    @Override
    public void remove(TreeExpenseBill treeExpenseBill) {
        throw new UnsupportedOperationException("Cannot remove from a leaf node");
    }

    @Override
    public String print() {
        return "ExpenseBillLeaf{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    public boolean isComposite() {
        return false;
    }
}