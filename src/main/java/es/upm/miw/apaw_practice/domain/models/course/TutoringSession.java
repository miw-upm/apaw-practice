package es.upm.miw.apaw_practice.domain.models.course;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TutoringSession {
    private String title;
    private LocalDateTime dateTime;
    private BigDecimal price;

    public TutoringSession() {
        //empty for framework
    }

    public TutoringSession(String title, LocalDateTime dateTime, BigDecimal price) {
        this.title = title;
        this.dateTime = dateTime;
        this.price = price;
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
    public String toString() {
        return "TutoringSession{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
