package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private String barcode;
    private String description;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public Article() {
        //empty for framework
    }

    public Article(String barcode, String description, BigDecimal price, String provider) {
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public void doDefault() {
        if (Objects.isNull(provider)) {
            this.provider = "various";
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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
        return "Article{" +
                "barcode=" + barcode +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", provider='" + provider + '\'' +
                '}';
    }
}
