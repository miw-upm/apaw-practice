package es.upm.miw.apaw_practice.adapters.mongodb.shop.entities;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleCreation;
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
    private LocalDate registrationDate;
    @Indexed(unique = true)
    private Long barcode;
    private String description;
    private BigDecimal price;
    private String provider;

    public ArticleEntity() {
        //empty from framework
    }

    public ArticleEntity(ArticleCreation articleCreation) {
        BeanUtils.copyProperties(articleCreation, this);
        this.id = UUID.randomUUID().toString();
        this.registrationDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                ", description='" + description + '\'' +
                ", registrationDate=" + registrationDate +
                ", price=" + price +
                ", provider='" + provider + '\'' +
                '}';
    }
}
