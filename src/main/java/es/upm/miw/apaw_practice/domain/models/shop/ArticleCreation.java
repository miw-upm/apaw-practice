package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;

public class ArticleCreation {
    private Long barcode;
    private String description;
    private BigDecimal price;
    private String provider;

    public ArticleCreation() {
        //empty for framework
    }

    public ArticleCreation(Long barcode, String description, BigDecimal price, String provider) {
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "ArticleCreation{" +
                "barcode=" + barcode +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", provider='" + provider + '\'' +
                '}';
    }
}
