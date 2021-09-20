package es.upm.miw.apaw_practice.adapters.mongodb.shop.entities;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document
public class ArticleEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String barcode;
    private String summary;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public ArticleEntity() {
        //empty from framework
    }

    public ArticleEntity(Article article) {
        BeanUtils.copyProperties(article, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void fromArticle(Article article) {
        BeanUtils.copyProperties(article, this);
    }

    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }

    @Override
    public int hashCode() {
        return barcode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (barcode.equals(((ArticleEntity) obj).barcode));
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", summary='" + summary + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", provider='" + provider + '\'' +
                '}';
    }
}
