package es.upm.miw.apaw_practice.domain.models.Hospital;

public class HistoriaMedical{
    private Long id;
    private LocalDateTime appointmentDate;
    private BigDecimal cost;
    private Boolean completed;

    public HistoriaMedical() {
    }

    public HistoriaMedical(Long id, LocalDateTime appointmentDate, BigDecimal cost, Boolean completed) {
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
}