package es.upm.miw.apaw_practice.adapters.mongodb.course.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class TutoringSessionEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private LocalDateTime dateTime;
    private BigDecimal price;

    public TutoringSessionEntity() {
        //Empty for framework
    }

    public TutoringSessionEntity(String title, LocalDateTime dateTime, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dateTime = dateTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (title.equals(((TutoringSessionEntity) obj).title));
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public String toString() {
        return "TutoringSessionEntity{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
