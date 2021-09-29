package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    private LocalDate date;
    private LocalDateTime hour;
    private Boolean consumed;

    public Appointment(){
        //empty for framework
    }

    public Appointment(LocalDate date, LocalDateTime hour, Boolean consumed){
        this.date = date;
        this.hour = hour;
        this.consumed = consumed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    public Boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(Boolean consumed) {
        this.consumed = consumed;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", hour=" + hour + '\'' +
                ", consumed=" + consumed +
                '}';
    }

}
