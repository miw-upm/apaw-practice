package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private String barcode;
    private String summary;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public Article() {
        //empty for framework
    }

    public Article(String barcode, String summary, BigDecimal price, String provider) {
        this.barcode = barcode;
        this.summary = summary;
        this.price = price;
        this.provider = provider;
    }

    public static Article ofBarcode(Article article) {
        Article articleDto = new Article();
        articleDto.setBarcode(article.getBarcode());
        return articleDto;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
                ", summary='" + summary + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", provider='" + provider + '\'' +
                '}';
    }
}
