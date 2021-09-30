package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities;

public class DiagnosisEntity {
    private String description;
    private String medicine;
    private Boolean critical;

    public DiagnosisEntity() {
        //empty for framework
    }

    public DiagnosisEntity(String description, String medicine, Boolean critical) {
        this.description = description;
        this.medicine = medicine;
        this.critical = critical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }
}
