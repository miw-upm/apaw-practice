package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public class Diagnosis {
    private String description;
    private String medicine;
    private Boolean critical;

    public Diagnosis() {
        //empty for framework
    }

    public Diagnosis(String description, String medicine, Boolean critical) {
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

    @Override
    public String toString() {
        return "Diagnosis{" +
                "description='" + description + '\'' +
                ", medicine='" + medicine + '\'' +
                ", critical=" + critical +
                '}';
    }
}
