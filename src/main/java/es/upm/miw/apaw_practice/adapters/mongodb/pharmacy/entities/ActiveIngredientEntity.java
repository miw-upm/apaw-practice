package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ActiveIngredientEntity {

    @Id
    private String code;
    @DBRef
    private DrugEntity drugEntity;
    private List<String> components;
    private Integer dose;

    public ActiveIngredientEntity() {
        //empty for framework
    }

    public ActiveIngredientEntity(String code, List<String> components, Integer dose, DrugEntity drugEntity) {
        this.code = code;
        this.drugEntity = drugEntity;
        this.components = components;
        this.dose = dose;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public DrugEntity getDrugEntity() {
        return drugEntity;
    }

    public void setDrugEntity(DrugEntity drugEntity) {
        this.drugEntity = drugEntity;
    }

    public ActiveIngredient toActiveIngredient() {
        ActiveIngredient activeIngredient = new ActiveIngredient();
        BeanUtils.copyProperties(this, activeIngredient, "drugEntity");
        Drug drug = this.drugEntity.toDrug();
        activeIngredient.setDrug(drug);
        return activeIngredient;
    }

}
