package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.List;

public class PharmacyDrugsUpdating {

    List<Drug> drugs;
    private String registrationNumber;


    public PharmacyDrugsUpdating() {
        //empty for framework
    }

    public PharmacyDrugsUpdating(String registrationNumber, List<Drug> drugs) {
        this.registrationNumber = registrationNumber;
        this.drugs = drugs;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}