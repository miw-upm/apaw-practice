package es.upm.miw.apaw_practice.domain.models.course;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TutoringSession {
    private String title;
    private LocalDateTime dateTime;
    private BigDecimal cost;

    public TutoringSession() {
        //empty for framework
    }

    public TutoringSession(String title, LocalDateTime dateTime, BigDecimal cost) {
        this.title = title;
        this.dateTime = dateTime;
        this.cost = cost;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TutoringSession{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", cost=" + cost +
                '}';
    }
}
