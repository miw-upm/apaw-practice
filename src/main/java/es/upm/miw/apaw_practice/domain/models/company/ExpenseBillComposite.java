package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpenseBillComposite implements TreeExpenseBill {

    private String description;
    private List<TreeExpenseBill> children;

    public ExpenseBillComposite(String description) {
        this.description = description;
        this.children = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public BigDecimal getAmount() {
        return children.stream()
                .map(TreeExpenseBill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void add(TreeExpenseBill treeExpenseBill) {
        this.children.add(treeExpenseBill);
    }

    @Override
    public void remove(TreeExpenseBill treeExpenseBill) {
        this.children.remove(treeExpenseBill);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append("ExpenseBillComposite{")
                .append("description='").append(description).append('\'')
                .append(", totalAmount=").append(this.getAmount())
                .append(", children=[");
        for (TreeExpenseBill child : children) {
            builder.append(child.print()).append(", ");
        }
        builder.append("]}");
        return builder.toString();
    }

    public boolean isComposite() {
        return true;
    }

    public List<TreeExpenseBill> getChildren() {
        return children;
    }


}