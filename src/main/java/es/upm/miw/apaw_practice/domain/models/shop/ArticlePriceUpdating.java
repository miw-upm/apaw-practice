package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;

public class ArticlePriceUpdating {

    private String barcode;
    private BigDecimal price;

    public ArticlePriceUpdating() {
        //empty for framework
    }

    public ArticlePriceUpdating(String barcode, BigDecimal price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ArticlePriceUpdating{" +
                "barcode=" + barcode +
                ", price=" + price +
                '}';
    }
}
