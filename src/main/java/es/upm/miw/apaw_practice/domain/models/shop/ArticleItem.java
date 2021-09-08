package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;

public class ArticleItem {
    private Long barcode;
    private Integer amount;
    private BigDecimal discount;

    public ArticleItem() {
        //empty for framework
    }

    public ArticleItem(Long barcode, Integer amount, BigDecimal discount) {
        this.barcode = barcode;
        this.amount = amount;
        this.discount = discount;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "barcode=" + barcode +
                ", amount=" + amount +
                ", discount=" + discount +
                '}';
    }
}
