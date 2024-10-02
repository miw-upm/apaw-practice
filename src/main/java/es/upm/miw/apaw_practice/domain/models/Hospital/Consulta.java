package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Consulta {
    private  Long id;
    private LocalDateTime appointmentDate;
    private BigDecimal cost;
    private Boolean completed;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime appointmentDate, BigDecimal cost, Boolean completed) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.cost = cost;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Consulta{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", cost=" + cost +
                ", completed=" + completed +
                '}';
    }
}