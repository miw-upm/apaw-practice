package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.time.LocalDateTime;
import java.util.List;

public class Prescription {

    private String id;
    private List<Drug> drug;
    private LocalDateTime prescriptionTimestamp;
    private Clinician clinician;

    public Prescription(String id, List<Drug> drug, LocalDateTime prescriptionTimestamp, Clinician clinician) {
        this.id = id;
        this.drug = drug;
        this.prescriptionTimestamp = prescriptionTimestamp;
        this.clinician = clinician;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Drug> getDrug() {
        return drug;
    }

    public void setDrug(List<Drug> drug) {
        this.drug = drug;
    }

    public LocalDateTime getPrescriptionTimestamp() {
        return prescriptionTimestamp;
    }

    public void setPrescriptionTimestamp(LocalDateTime prescriptionTimestamp) {
        this.prescriptionTimestamp = prescriptionTimestamp;
    }

    public Clinician getClinician() {
        return clinician;
    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;
    }
}
