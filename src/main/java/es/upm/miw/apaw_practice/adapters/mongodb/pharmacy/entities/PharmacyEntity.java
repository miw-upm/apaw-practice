package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document
public class PharmacyEntity {

    @Id
    private String registrationNumber;
    private String address;
    private Integer postalCode;
    @DBRef
    List<DrugEntity> drugEntities;

    public PharmacyEntity() {
        //empty for framework
    }

    public PharmacyEntity(Pharmacy pharmacy) {
        BeanUtils.copyProperties(pharmacy, this,"drugEntities");
        List<DrugEntity> drugs = pharmacy.getDrugs().stream()
                .map(DrugEntity::new)
                .collect(Collectors.toList());
        this.setDrugEntities(drugs);
    }

    public PharmacyEntity(String registrationNumber, String address, Integer postalCode, List<DrugEntity> drugEntities) {
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.drugEntities = drugEntities;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public List<DrugEntity> getDrugEntities() {
        return drugEntities;
    }

    public void setDrugEntities(List<DrugEntity> drugEntities) {
        this.drugEntities = drugEntities;
    }

    public Pharmacy toPharmacy() {
        Pharmacy pharmacy = new Pharmacy();
        BeanUtils.copyProperties(this, pharmacy, "drugEntities");
        List<Drug> drugs = this.drugEntities.stream()
                .map(DrugEntity::toDrug)
                .collect(Collectors.toList());
        pharmacy.setDrugs(drugs);
        return pharmacy;
    }

    public void fromPharmacy(Pharmacy pharmacy) {
        BeanUtils.copyProperties(pharmacy, this);
    }

}
